package com.sergiandreplace.testabean;

import com.sergiandreplace.testabean.annotation.NotABeanField;
import com.sergiandreplace.testabean.annotation.TargetClass;
import com.sergiandreplace.testabean.exception.FieldException;
import com.sergiandreplace.testabean.generator.Generator;
import com.sergiandreplace.testabean.generator.GeneratorFactory;
import org.apache.commons.lang3.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.AbstractList;
import java.util.List;


public class TestABeanTest {

    private TestABean testABean;

    @Before
    public void setUp() {
        Configuration configuration=new Configuration.Builder().setExceptions("thisOneWillBeIgnored").build();
        testABean = new TestABean(TestableBean.class, configuration);

    }

    @Test
    public void whenFieldsRequest_ShouldReturnRightList() {
        List<Field> testableFields=testABean.getTestableFields();
        Assertions.assertThat(testableFields).extracting("name").containsOnly("aField","anotherField","floatField","someBoolean", "abstractList");
    }

    @Test
    public void whenTestingRightBean_ShouldWorkPerfect() throws NoSuchMethodException, FieldException, NoSuchFieldException {
        testABean.test();
    }


    @Test(expected = FieldException.class)
    public void whenTestingBeanMissingASetter_ShouldLaunchException() throws NoSuchMethodException, FieldException, NoSuchFieldException {
        testABean = new TestABean(BeanMissingSetter.class);
        testABean.test();
    }

    @Test(expected = FieldException.class)
    public void whenTestingBeanMissingAGetter_ShouldLaunchException() throws  FieldException {
        testABean = new TestABean(BeanMissingGetter.class);
        testABean.test();
    }

    @Test(expected = FieldException.class)
    public void whenTestingBeanWithUnknownTypes_ShouldLaunchException() throws FieldException {
        testABean = new TestABean(BeanWithUnknownType.class);
        testABean.test();
    }

    @Test
    public void whenTestingBeanWithUnknownTypeAndGeneratorDone_ShouldWork() throws FieldException {
        GeneratorFactory generatorFactory=new GeneratorFactory();
        generatorFactory.add(new UnknownTypeGenerator());
        Configuration configuration=new Configuration.Builder().setGeneratorFactory(generatorFactory).build();
        testABean = new TestABean(BeanWithUnknownType.class, configuration);
        testABean.test();

    }

    public static class TestableBean {
        private String aField;
        private String anotherField;
        @NotABeanField
        private String ignoreThisOne;
        private String thisOneWillBeIgnored;
        private float floatField;
        private boolean someBoolean;
        private AbstractList<String> abstractList;

        public String getAField() {
            return aField;
        }

        public void setAField(String aField) {
            this.aField = aField;
        }

        public String getAnotherField() {
            return anotherField;
        }

        public void setAnotherField(String anotherField) {
            this.anotherField = anotherField;
        }

        public String getIgnoreThisOne() {
            return ignoreThisOne;
        }

        public void setIgnoreThisOne(String ignoreThisOne) {
            this.ignoreThisOne = ignoreThisOne;
        }

        public float getFloatField() {
            return floatField;
        }

        public void setFloatField(float floatField) {
            this.floatField = floatField;
        }

        public boolean isSomeBoolean() {
            return someBoolean;
        }

        public void setSomeBoolean(boolean someBoolean) {
            this.someBoolean = someBoolean;
        }

        public AbstractList<String> getAbstractList() {
            return abstractList;
        }

        public void setAbstractList(AbstractList<String> abstractList) {
            this.abstractList = abstractList;
        }
    }

    public static class BeanMissingSetter {
        private Long field;

        public Long getField() {
            return field;
        }
    }

    public static class BeanMissingGetter {
        private float field;

        public void setField(float field) {
            this.field = field;
        }
    }

    public static class BeanWithUnknownType {
        UnknownType unknownValue;

        public UnknownType getUnknownValue() {
            return unknownValue;
        }

        public void setUnknownValue(UnknownType unknownValue) {
            this.unknownValue = unknownValue;
        }
    }

    public static class UnknownType {
        String value;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            UnknownType that = (UnknownType) o;

            return value != null ? value.equals(that.value) : that.value == null;

        }

        @Override
        public int hashCode() {
            return value != null ? value.hashCode() : 0;
        }
    }

    @TargetClass(UnknownType.class)
    public static class UnknownTypeGenerator implements Generator<UnknownType> {

        @Override
        public UnknownType next() {
            UnknownType unknownType=new UnknownType();
            unknownType.setValue(RandomStringUtils.randomAlphanumeric(100));
            return unknownType;
        }
    }

}