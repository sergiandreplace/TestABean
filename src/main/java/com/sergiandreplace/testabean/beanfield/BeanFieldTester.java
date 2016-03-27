package com.sergiandreplace.testabean.beanfield;


import com.sergiandreplace.testabean.exception.FieldException;
import com.sergiandreplace.testabean.generator.Generator;
import com.sergiandreplace.testabean.generator.GeneratorFactory;

import java.lang.reflect.InvocationTargetException;

public class BeanFieldTester {

    private final GeneratorFactory generatorFactory;
    private final BeanField beanField;

    public BeanFieldTester(BeanField beanField, GeneratorFactory generatorFactory){
        this.beanField = beanField;
        this.generatorFactory=generatorFactory;

    }

    public void checkAssignment() throws FieldException {
        Object instance=instantiateBean();
        Object value=getGenerator().next();
        executeSetter(instance, value);
        Object returnedValue=executeGetter(instance);
        if (!value.equals(returnedValue)) {
            throw new FieldException("Field " + beanField.getFieldName() + " assignment not working as expected\n" +
                    "Expected: " + value.toString() + "\n" +
                    "Obtained: " + returnedValue, beanField.getFieldName());
        }
    }

    public void checkAssignment(int times) throws FieldException {

        for (int i=0;i<times;i++) {
            checkAssignment();
        }

    }

    public void checkTransitivePropertyWorking() throws FieldException {
        Object instanceX=instantiateBean();
        Object instanceY=instantiateBean();
        Object instanceZ=instantiateBean();

        Object value=getGenerator().next();

        executeSetter(instanceX, value);
        executeSetter(instanceY, executeGetter(instanceX));
        executeSetter(instanceZ, executeGetter(instanceY));

        Object returnedValue = executeGetter(instanceZ);

        if (!value.equals(returnedValue)) {
            throw new FieldException("Field " + beanField.getFieldName() + " transitive property not working as expected\n" +
                    "Expected: " + value.toString() + "\n" +
                    "Obtained: " + returnedValue, beanField.getFieldName());
        }

    }

    public void checkTransitivePropertyWorking(int times) throws FieldException {
        for (int i=0;i<times ;i++) {
            checkTransitivePropertyWorking();
        }
    }



    private Object executeGetter(Object instance) throws FieldException {
        try {
            return beanField.getGetter().invoke(instance);
        } catch (IllegalAccessException e) {
            throw new FieldException("Cannot access to getter for field " + beanField.getFieldName(), beanField.getFieldName(), e);
        } catch (InvocationTargetException e) {
            throw new FieldException("Exception returned by getter for field " + beanField.getFieldName(), beanField.getFieldName(), e);
        }
    }

    private void executeSetter(Object instance, Object value) throws FieldException {
        try {
            beanField.getSetter().invoke(instance, value);
        } catch (IllegalAccessException e) {
            throw new FieldException("Cannot access to setter for field " + beanField.getFieldName(), beanField.getFieldName(), e);
        } catch (InvocationTargetException e) {
            throw new FieldException("Exception returned by setter for field " + beanField.getFieldName(), beanField.getFieldName(), e);
        }
    }

    private Object instantiateBean() throws FieldException {
        try {
            return beanField.getClazz().getConstructor().newInstance();
        } catch (InstantiationException e) {
            throw new FieldException("Unable to execute constructor abstract class " + beanField.getClazz().getCanonicalName(), beanField.getFieldName(), e);
        } catch (IllegalAccessException e) {
            throw new FieldException("Cannot access to constructor for bean " + beanField.getClazz().getCanonicalName(), beanField.getFieldName(), e);
        } catch (InvocationTargetException e) {
            throw new FieldException("Exception returned by constructor for bean " + beanField.getClazz().getCanonicalName(), beanField.getFieldName(), e);
        } catch (NoSuchMethodException e) {
            throw new FieldException("Unable to find a constructor for bean " + beanField.getClazz().getCanonicalName(), beanField.getFieldName(), e);
        }
    }

    public Generator getGenerator() throws FieldException {
        Generator generator=generatorFactory.get(beanField.getField().getType());
        if (generator==null) {
            throw new FieldException(String.format("Value generator not available for field %s of type %s.", beanField.getFieldName(), beanField.getField().getType().getCanonicalName()),beanField.getFieldName());
        }
        return generator;
    }




}
