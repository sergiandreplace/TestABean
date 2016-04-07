package com.sergiandreplace.testabean.generator;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sergi on 4/4/16.
 */
public class PrimitiveUtil {

    private static Map<Class, Class> wrappers;


     static {
        wrappers = new HashMap<>();
        wrappers.put(int.class, Integer.class);
        wrappers.put(short.class, Short.class);
        wrappers.put(byte.class, Byte.class);
        wrappers.put(char.class, Character.class);
        wrappers.put(long.class, Long.class);
        wrappers.put(float.class, Float.class);
        wrappers.put(double.class, Double.class);
        wrappers.put(boolean.class, Boolean.class);
    }

    public static Class getWrapper(Class clazz) {
        if (isPrimitive(clazz)) {
            return wrappers.get(clazz);
        }
        return clazz;
    }

    public static boolean isPrimitive(Class clazz) {
        return wrappers.containsKey(clazz);
    }

}
