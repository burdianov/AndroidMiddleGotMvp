package com.testography.androidmiddlegot.utils;

public class Utils {
    public static int getIdFromUri(String uri) {
        String id;
        int index;

        index = uri.lastIndexOf('/');
        id = uri.substring(index + 1);
        return Integer.parseInt(id);
    }
}
