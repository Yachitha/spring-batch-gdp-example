package com.assesment.rootcodelabs.util;

import com.assesment.rootcodelabs.exception.GeneralError;
import com.assesment.rootcodelabs.exception.RequestFailedException;
import com.google.gson.Gson;

public class SharedMethods {
    private static SharedMethods instance;

    public static SharedMethods getInstance() {
        if (instance == null) {
            instance = new SharedMethods();
        }
        return instance;
    }

    public static String getErrorMessageString(GeneralError error) {
        try {
            Gson gson = new Gson();
            return gson.toJson(error);
        } catch (Exception ex) {
            if (error != null) {
                return "INVALID DATA: " + error.getErrorMessage();
            } else {
                return "INVALID DATA: " + RequestFailedException.FAULT_MESSAGE;
            }
        }
    }
}
