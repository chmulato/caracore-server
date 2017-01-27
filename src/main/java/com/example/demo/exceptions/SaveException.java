package com.example.demo.exceptions;

/**
 * Created by jarbas on 09/10/15.
 */
public class SaveException extends BaseRuntimeException {

    static final long serialVersionUID = 1L;

    private Object object;

    public SaveException(Throwable cause, Object object) {
        super(cause);
        this.object = object;
    }

    public Object getObject() {
        return object;
    }
}
