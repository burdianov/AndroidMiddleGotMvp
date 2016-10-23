package com.testography.androidmiddlegot.data.network.res;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.testography.androidmiddlegot.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class HouseModelRes {

    @SerializedName("url")
    @Expose
    private String id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("coatOfArms")
    @Expose
    private String coatOfArms;

    @SerializedName("words")
    @Expose
    private String words;

    @SerializedName("swornMembers")
    @Expose
    private List<String> swornMembers = new ArrayList<>();

    public String getId() {
        return String.valueOf(Utils.getIdFromUri(this.id));
    }

    public String getName() {
        return name;
    }

    public String getCoatOfArms() {
        return coatOfArms;
    }

    public String getWords() {
        return words;
    }

    public List<String> getSwornMembers() {
        return swornMembers;
    }
}
