package com.sergiandreplace.testabean.exception;


public class FieldException extends Exception {
    private String field;

    public FieldException(String message, String field) {
        super(message);
        setField(field);
    }

    public FieldException(String message, String field, Throwable cause) {
        super(message,cause);
        setField(field);
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}
