package com.usermgt.utils;

import com.google.gson.Gson;

public class StringUtils {

    public static String objectToJson(Object obj) {
        return new Gson().toJson(obj);
    }
}
