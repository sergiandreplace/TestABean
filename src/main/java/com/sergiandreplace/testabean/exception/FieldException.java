package com.sergiandreplace.testabean.exception;


public class FieldException extends Exception {
    private String field;

    public FieldException(String message, String field) {
        super(message);
        this.field=field;
    }

    public FieldException(String message, String field, Throwable cause) {
        super(message,cause);
        this.field=field;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}
