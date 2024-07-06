package com.example.orderService.utilities;

import org.json.JSONObject;

import java.util.List;

public class Utilities {

    public  static <T> String dataReturn(T obj) {
        return new JSONObject()
                .put(Constants.erc, Constants.PROCESS_SUCCESS)
                .put(Constants.data, obj).toString();
    }

    public  static String successMessageReturn(String msg) {
        return new JSONObject()
                .put(Constants.erc, Constants.PROCESS_SUCCESS)
                .put(Constants.msg, msg).toString();
    }
    public  static String errorMessageReturn(String msg) {
        return new JSONObject()
                .put(Constants.erc, Constants.PROCESS_FAILED)
                .put(Constants.msg, msg).toString();
    }


    public static boolean isNullOrEmpty(String elem) {
        return elem == null || elem.isEmpty();
    }
    public static boolean isNullOrEmpty(List list) {
        return list == null || list.size() == 0;
    }
}
