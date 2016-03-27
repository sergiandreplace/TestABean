package com.sergiandreplace.testabean.beanfield;

import com.sergiandreplace.testabean.exception.FieldException;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;


public class BeanFieldBuilderTest {

    private BeanFieldBuilder beanFieldBuilder;

    @Before
    public void setUp() {
        beanFieldBuilder = new BeanFieldBuilder(TestableBean.class);
    }


    @Test(expected = FieldException.class)
    public void whenFieldDoesNotExist_ShouldLaunchException() throws FieldException {
        beanFieldBuilder.build("nonExistingField");
    }

    @Test(expected = FieldException.class)
    public void whenFieldHasNoSetter_ShouldLaunchException() throws FieldException {
        beanFieldBuilder.build("fieldWithoutSetter");
    }

    @Test(expected = FieldException.class)
    public void whenFieldHasNoGetter_ShouldLaunchException() throws FieldException {
        beanFieldBuilder.build("fieldWithoutGetter");
    }

    @Test
    public void whenFieldIsOk_ShouldFindGetter() throws FieldException, NoSuchMethodException {
        BeanField beanField = beanFieldBuilder.build("goodField");
        Assertions.assertThat(beanField.getGetter())
                .isEqualTo(TestableBean.class.getDeclaredMethod("getGoodField"));
    }

    @Test
    public void whenFieldIsOk_ShouldFindSetter() throws FieldException, NoSuchMethodException {
        BeanField beanField = beanFieldBuilder.build("goodField");
        Assertions.assertThat(beanField.getSetter())
                .isEqualTo(TestableBean.class.getDeclaredMethod("setGoodField", String.class));
    }

    @Test
    public void whenFieldIsBoolean_ShouldFindIsGetter() throws FieldException, NoSuchMethodException {
        BeanField beanField = beanFieldBuilder.build("goodBooleanField");
        Assertions.assertThat(beanField.getGetter())
                .isEqualTo(TestableBean.class.getDeclaredMethod("isGoodBooleanField"));
    }

    @Test
    public void whenFieldIsBooleanPrimitive_ShouldFindIsGetter() throws FieldException, NoSuchMethodException {
        BeanField beanField = beanFieldBuilder.build("goodBooleanPrimitiveField");
        Assertions.assertThat(beanField.getGetter())
                .isEqualTo(TestableBean.class.getDeclaredMethod("isGoodBooleanPrimitiveField"));
    }

    @Test(expected = FieldException.class)
    public void whenBooleanGetterIsNotUsingIs_ShouldRaiseException() throws FieldException {
        beanFieldBuilder.build("badBooleanField");

    }


    public class TestableBean {
        private String goodField;
        private Boolean goodBooleanField;
        private boolean goodBooleanPrimitiveField;
        private boolean badBooleanField;
        private String fieldWithoutSetter;
        private String fieldWithoutGetter;

        public String getGoodField() {
            return goodField;
        }

        public void setGoodField(String goodField) {
            this.goodField = goodField;
        }

        public Boolean isGoodBooleanField() {
            return goodBooleanField;
        }

        public void setGoodBooleanField(Boolean goodBooleanField) {
            this.goodBooleanField = goodBooleanField;
        }

        public String getFieldWithoutSetter() {
            return fieldWithoutSetter;
        }

        public void setFieldWithoutGetter(String fieldWithoutGetter) {
            this.fieldWithoutGetter = fieldWithoutGetter;
        }

        public boolean isGoodBooleanPrimitiveField() {
            return goodBooleanPrimitiveField;
        }

        public void setGoodBooleanPrimitiveField(boolean goodBooleanPrimitiveField) {
            this.goodBooleanPrimitiveField = goodBooleanPrimitiveField;
        }

        public boolean getBadBooleanField() {
            return badBooleanField;
        }

        public void setBadBooleanField(boolean badBooleanField) {
            this.badBooleanField = badBooleanField;
        }
    }

}