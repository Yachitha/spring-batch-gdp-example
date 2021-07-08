package com.assesment.rootcodelabs.util;

public class StringHandler {
    private static StringHandler sharedInstance;

    public static StringHandler getSharedInstance () {
        if (sharedInstance == null) {
            sharedInstance = new StringHandler();
        }

        return sharedInstance;
    }

    public static Boolean checkForNullOrEmpty(String input) {
        Boolean result = false;
        if (input != null && input.length() > 0 && !input.isEmpty()) {
            result = true;
        }
        return result;
    }
}
