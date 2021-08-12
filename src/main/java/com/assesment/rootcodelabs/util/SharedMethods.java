package com.assesment.rootcodelabs.util;

import com.assesment.rootcodelabs.exception.GeneralError;
import com.assesment.rootcodelabs.exception.RequestFailedException;

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
            return "Error: " + error.getErrorMessage() + "\ncode: " + error.getErrorCode();
        } catch (Exception ex) {
            if (error != null) {
                return "INVALID DATA: " + error.getErrorMessage();
            } else {
                return "INVALID DATA: " + RequestFailedException.FAULT_MESSAGE;
            }
        }
    }
}
