package com.sergiandreplace.testabean.util;


public class MethodNameUtils {

    private static final String GETTER_PREFIX = "get";
    private static final String SETTER_PREFIX = "set";
    private static final String BOOLEAN_GETTER_PREFIX = "is";

    public static String getUpperCamelCase(String name) {
        checkString(name);
        String firstLetter=name.substring(0,1).toUpperCase();
        return firstLetter + name.substring(1);
    }

    public static void checkString(String name) {
        if (name==null) {
            throw new NullPointerException();
        }
        if (name.length()<1) {
            throw new StringIndexOutOfBoundsException();
        }
    }

    public static String getFieldGetter(String field) {
        return GETTER_PREFIX + getUpperCamelCase(field);
    }


    public static String getFieldIsGetter(String field) {
        return BOOLEAN_GETTER_PREFIX + getUpperCamelCase(field);
    }


    public static String getFieldSetter(String field) {
        return SETTER_PREFIX + getUpperCamelCase(field);
    }
}
