package com.sergiandreplace.testabean;

import com.sergiandreplace.testabean.annotation.NotABeanField;
import com.sergiandreplace.testabean.beanfield.BeanField;
import com.sergiandreplace.testabean.beanfield.BeanFieldBuilder;
import com.sergiandreplace.testabean.beanfield.BeanFieldTester;
import com.sergiandreplace.testabean.exception.FieldException;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


public class TestABean {

    private final Class clazz;
    private final Configuration configuration;


    public TestABean(Class clazz) {
        this.clazz = clazz;
        this.configuration = new Configuration.Builder().build();
    }

    public TestABean(Class clazz, Configuration configuration) {
        this.clazz = clazz;
        this.configuration = configuration;
    }

    public void test() throws FieldException {
        BeanFieldBuilder beanFieldBuilder=new BeanFieldBuilder(clazz);
        List<Field> testableFields=getTestableFields();
        for (Field field:testableFields) {
            BeanField beanField=beanFieldBuilder.build(field);
            BeanFieldTester beanFieldTester=new BeanFieldTester(beanField, configuration.getGeneratorFactory());
            beanFieldTester.checkAssignment(configuration.getTestingRepetitions());
            beanFieldTester.checkTransitivePropertyWorking(configuration.getTestingRepetitions());
        }
    }

    public List<Field> getTestableFields() {
        List<Field> testableFields = new ArrayList<>();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field:fields) {
            if (!fieldShouldBeIgnored(field)) {
                testableFields.add(field);
            }
        }
        return testableFields;
    }

    private boolean fieldShouldBeIgnored(Field field) {
        return isInExceptionList(field) || isAnnotatedAsNotABeanField(field);
    }

    private boolean isAnnotatedAsNotABeanField(Field field) {
        return field.isAnnotationPresent(NotABeanField.class);
    }

    private boolean isInExceptionList(Field field) {
        return configuration.getExceptions().contains(field.getName());
    }
}
