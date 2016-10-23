package com.testography.androidmiddlegot.data.network.res;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.testography.androidmiddlegot.utils.Utils;

import java.util.ArrayList;

public class SwornMemberModelRes {

    @SerializedName("url")
    @Expose
    private String id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("born")
    @Expose
    private String born;

    @SerializedName("died")
    @Expose
    private String died;

    @SerializedName("titles")
    @Expose
    private ArrayList<String> titles = new ArrayList<>();

    @SerializedName("aliases")
    @Expose
    private ArrayList<String> aliases = new ArrayList<>();

    @SerializedName("father")
    @Expose
    private String father;

    @SerializedName("mother")
    @Expose
    private String mother;

    public String getId() {
        return String.valueOf(Utils.getIdFromUri(this.id));
    }

    public String getName() {
        return name;
    }

    public String getBorn() {
        return born;
    }

    public String getDied() {
        return died;
    }

    public String getTitles() {
        String result = "";

        if (titles != null) {
            for (String title : titles) {
                result += " " + title;
            }
        }
        return result;
    }

    public String getAliases() {
        String result = "";

        if (aliases != null) {
            for (String alias : aliases) {
                result += " " + alias;
            }
        }
        return result;
    }

    public String getFather() {
        return father;
    }

    public String getMother() {
        return mother;
    }
}
