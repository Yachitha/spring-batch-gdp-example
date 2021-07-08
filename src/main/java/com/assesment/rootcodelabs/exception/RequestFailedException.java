package com.assesment.rootcodelabs.exception;

import com.assesment.rootcodelabs.util.SharedMethods;

public class RequestFailedException extends Exception {
    private String faultCode;
    private String faultMessage;
    public static String FAULT_MESSAGE = "Internal Error occured";

    public static final String FIELDS_NOT_VALID = "Invalid";

    public RequestFailedException(Throwable throwable) {
        super(throwable);
    }

    public RequestFailedException(GeneralError generalError) {
        super(SharedMethods.getErrorMessageString(generalError));
        faultCode = generalError.getErrorCode();
        faultMessage = generalError.getErrorMessage();
    }
}
