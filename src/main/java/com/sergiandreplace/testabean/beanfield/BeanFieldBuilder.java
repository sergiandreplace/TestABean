package com.sergiandreplace.testabean.beanfield;

import com.sergiandreplace.testabean.exception.FieldException;
import com.sergiandreplace.testabean.util.MethodNameUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;


public class BeanFieldBuilder {
    private final Class clazz;

    public BeanFieldBuilder(Class clazz) {

        this.clazz = clazz;
    }

    public BeanField build(String fieldName) throws FieldException {
        BeanField beanField=new BeanField();
        beanField.setClazz(clazz);

        beanField.setFieldName(fieldName);
        beanField.setField(getField(fieldName));

        beanField.setGetterName(getGetterName(beanField.getField()));
        beanField.setGetter(getGetter(beanField));

        beanField.setSetterName(getSetterName(beanField.getField()));
        beanField.setSetter(getSetter(beanField));

        return beanField;
    }

    public BeanField build(Field field) throws FieldException {
        return build(field.getName());
    }

    private Field getField(String fieldName) throws FieldException {
        try {
            return clazz.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            throw new FieldException(String.format("Unable to find field %s", fieldName),fieldName,e);
        }
    }

    private String getGetterName(Field field) {
        if (field.getType().isAssignableFrom(boolean.class) ||
                field.getType().isAssignableFrom(Boolean.class)) {
            return MethodNameUtils.getFieldIsGetter(field.getName());
        }else{
            return MethodNameUtils.getFieldGetter(field.getName());
        }

    }

    private Method getGetter(BeanField beanField) throws FieldException {

            try {
               return clazz.getDeclaredMethod(beanField.getGetterName());
            } catch (NoSuchMethodException e) {
                throw new FieldException(String.format(
                        "Getter not found for field %s. %s expected.",
                        beanField.getFieldName(), beanField.getGetterName()),
                        beanField.getFieldName(), e);
            }
    }


    private String getSetterName(Field field) {
        return MethodNameUtils.getFieldSetter(field.getName());
    }

    private Method getSetter(BeanField beanField) throws FieldException {
        try {
            return clazz.getDeclaredMethod(beanField.getSetterName(), beanField.getField().getType());
        } catch (NoSuchMethodException e) {
            throw new FieldException(String.format(
                    "Setter not found for field %s. %s expected.",
                    beanField.getFieldName(), beanField.getSetterName()),
                    beanField.getFieldName(), e);
        }
    }

}
