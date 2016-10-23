package com.testography.androidmiddlegot.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.testography.androidmiddlegot.R;
import com.testography.androidmiddlegot.data.managers.DataManager;
import com.testography.androidmiddlegot.data.storage.models.DaoSession;
import com.testography.androidmiddlegot.data.storage.models.SwornMember;
import com.testography.androidmiddlegot.data.storage.models.SwornMemberDao;
import com.testography.androidmiddlegot.ui.activities.MainActivity;
import com.testography.androidmiddlegot.ui.adapters.SwornMembersAdapter;
import com.testography.androidmiddlegot.utils.ConstantsManager;

import java.util.ArrayList;
import java.util.List;

public class HouseOneFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private SwornMembersAdapter mSwornMembersAdapter;

    private DaoSession mDaoSession;

    public HouseOneFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_house_one, container,
                false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view_one);

        mDaoSession = DataManager.getInstance().getDaoSession();

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);

        List<String> names = new ArrayList<>();
        List<String> remoteIds = new ArrayList<>();

        List<SwornMember> membersList = getSwornMembersFromDb(String
                .valueOf(ConstantsManager.houseOne));

        for (SwornMember member :
                membersList) {
            names.add(member.getName());
            remoteIds.add(member.getRemoteId());
        }

        mSwornMembersAdapter = new SwornMembersAdapter((MainActivity) getActivity(),
                names, remoteIds);
        mRecyclerView.setAdapter(mSwornMembersAdapter);

        return rootView;
    }

    private List<SwornMember> getSwornMembersFromDb(String houseId) {
        List<SwornMember> membersList = new ArrayList<>();

        try {
            membersList = mDaoSession.queryBuilder(SwornMember.class)
                    .where(SwornMemberDao.Properties.HouseRemoteId.eq(houseId))
                    .orderDesc(SwornMemberDao.Properties.Name)
                    .build()
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return membersList;
    }
}