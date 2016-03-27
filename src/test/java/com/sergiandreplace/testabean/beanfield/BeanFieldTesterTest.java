package com.sergiandreplace.testabean.beanfield;

import com.sergiandreplace.testabean.exception.FieldException;
import com.sergiandreplace.testabean.generator.Generator;
import com.sergiandreplace.testabean.generator.GeneratorFactory;
import com.sergiandreplace.testabean.generator.StringGenerator;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

import static org.assertj.core.api.Assertions.assertThat;


public class BeanFieldTesterTest {

    private BeanFieldTester beanFieldTester;
    private BeanField beanField;
    private BeanField wrongBeanField;

    @Before
    public void setUp() {
        beanField = new BeanField();
        beanFieldTester = new BeanFieldTester(beanField,new GeneratorFactory());

    }

    @Test
    public void whenGeneratorRequested_ShouldReturnRightOne() throws NoSuchFieldException, FieldException {

        beanField.setField(TestedBean.class.getDeclaredField("stringField"));

        Generator generator = beanFieldTester.getGenerator();
        assertThat(generator).isInstanceOf(StringGenerator.class);
    }

    @Test
    public void whenAssignmentComparedWithRightObject_ShouldWorkFine() throws IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException, NoSuchFieldException, FieldException {

        beanField.setClazz(TestedBean.class);
        beanField.setField(TestedBean.class.getDeclaredField("stringField"));
        beanField.setGetter(TestedBean.class.getDeclaredMethod("getStringField"));
        beanField.setSetter(TestedBean.class.getDeclaredMethod("setStringField", String.class));


        beanFieldTester.checkAssignment();

    }

    @Test
    public void whenAssignmentComparedWithRightObjectManyTimes_ShouldWorkFine() throws FieldException, NoSuchFieldException, NoSuchMethodException {

        beanField.setClazz(TestedBean.class);
        beanField.setField(TestedBean.class.getDeclaredField("stringField"));
        beanField.setGetter(TestedBean.class.getDeclaredMethod("getStringField"));
        beanField.setSetter(TestedBean.class.getDeclaredMethod("setStringField", String.class));

        beanFieldTester.checkAssignment(100);

    }

    @Test(expected = FieldException.class)
    public void whenAssignmentComparedWithWrongObject_ShouldLaunchException() throws NoSuchFieldException, NoSuchMethodException, FieldException{

        beanField.setClazz(TestedBean.class);
        beanField.setField(TestedBean.class.getDeclaredField("wrongStringField"));
        beanField.setGetter(TestedBean.class.getDeclaredMethod("getWrongStringField"));
        beanField.setSetter(TestedBean.class.getDeclaredMethod("setWrongStringField", String.class));

        beanFieldTester.checkAssignment();
    }

    @Test(expected = FieldException.class)
    public void whenAssignmentComparedWithWrongObjectManyTimes_ShouldLaunchException() throws NoSuchFieldException, NoSuchMethodException, FieldException {

        beanField.setClazz(TestedBean.class);
        beanField.setField(TestedBean.class.getDeclaredField("wrongStringField"));
        beanField.setGetter(TestedBean.class.getDeclaredMethod("getWrongStringField"));
        beanField.setSetter(TestedBean.class.getDeclaredMethod("setWrongStringField", String.class));

        beanFieldTester.checkAssignment(100);
    }

    @Test
    public void whenTransitivePropertyComparedWithRightObject_ShouldWorkFine() throws NoSuchFieldException, NoSuchMethodException, FieldException {

        beanField.setClazz(TestedBean.class);
        beanField.setField(TestedBean.class.getDeclaredField("stringField"));
        beanField.setGetter(TestedBean.class.getDeclaredMethod("getStringField"));
        beanField.setSetter(TestedBean.class.getDeclaredMethod("setStringField", String.class));

        beanFieldTester.checkTransitivePropertyWorking();

    }

    @Test
    public void whenTransitivePropertyWithRightObjectManyTimes_ShouldWorkFine() throws NoSuchFieldException, NoSuchMethodException, FieldException {

        beanField.setClazz(TestedBean.class);
        beanField.setField(TestedBean.class.getDeclaredField("stringField"));
        beanField.setGetter(TestedBean.class.getDeclaredMethod("getStringField"));
        beanField.setSetter(TestedBean.class.getDeclaredMethod("setStringField", String.class));

        beanFieldTester.checkTransitivePropertyWorking(100);

    }

    @Test(expected = FieldException.class)
    public void whensTransitivePropertyComparedWithWrongObject_ShouldLaunchException() throws NoSuchFieldException, NoSuchMethodException, FieldException {

        beanField.setClazz(TestedBean.class);
        beanField.setField(TestedBean.class.getDeclaredField("wrongStringField"));
        beanField.setGetter(TestedBean.class.getDeclaredMethod("getWrongStringField"));
        beanField.setSetter(TestedBean.class.getDeclaredMethod("setWrongStringField", String.class));

        beanFieldTester.checkTransitivePropertyWorking();
    }

    @Test(expected = FieldException.class)
    public void whensTransitivePropertyComparedWithWrongObjectManyTimes_ShouldLaunchException() throws NoSuchFieldException, NoSuchMethodException, FieldException {

        beanField.setClazz(TestedBean.class);
        beanField.setField(TestedBean.class.getDeclaredField("wrongStringField"));
        beanField.setGetter(TestedBean.class.getDeclaredMethod("getWrongStringField"));
        beanField.setSetter(TestedBean.class.getDeclaredMethod("setWrongStringField", String.class));

        beanFieldTester.checkTransitivePropertyWorking(100);
    }


    public static class TestedBean {
        private String stringField;
        private String wrongStringField;

        public String getStringField() {
            return stringField;
        }

        public void setStringField(String stringField) {
            this.stringField = stringField;
        }

        public String getWrongStringField() {
            return wrongStringField.toLowerCase();
        }

        public void setWrongStringField(String wrongStringField) {
            this.wrongStringField = wrongStringField;
        }
    }

}