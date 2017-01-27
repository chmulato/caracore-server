package com.example.demo.exceptions;

/**
 * Created by jarbas on 09/10/15.
 */
public class BaseException extends Exception {

    private static final long serialVersionUID = -5813782662252619704L;

    public BaseException() {
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
