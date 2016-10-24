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

import java.util.ArrayList;
import java.util.List;

public class HouseFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private SwornMembersAdapter mSwornMembersAdapter;

    private DaoSession mDaoSession;

    private int mHouseNumber;

    public HouseFragment() {

    }

    public static HouseFragment newInstance(int houseNumber) {
        Bundle bundle = new Bundle();
        bundle.putInt("houseNumber", houseNumber);

        HouseFragment fragment = new HouseFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    private void readBundle(Bundle bundle) {
        if (bundle != null) {
            mHouseNumber = bundle.getInt("houseNumber");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_house, container,
                false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);

        mDaoSession = DataManager.getInstance().getDaoSession();

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);

        List<String> names = new ArrayList<>();
        List<String> remoteIds = new ArrayList<>();

        readBundle(getArguments());

        List<SwornMember> membersList = getSwornMembersFromDb(String
                .valueOf(mHouseNumber));

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