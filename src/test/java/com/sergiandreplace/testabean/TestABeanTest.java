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
import java.util.Date;
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


    @Test
    public void whenTestingBeanMissingASetter_ShouldLaunchException() throws NoSuchMethodException, NoSuchFieldException {
        testABean = new TestABean(BeanMissingSetter.class);
        try {
            testABean.test();
            Assertions.fail("Assertion not captured when field setter missing");
        }catch (FieldException e) {
            Assertions.assertThat(e.getField()).isEqualTo("field");
        }
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

    @Test
    public void whenBeanWithArraysIsTest_ShouldWorkFine() throws FieldException {
        testABean=new TestABean(BeanWithArrays.class);
        testABean.test();
    }

    @Test
    public void whenBeanWithPrimitveArrayIsTested_ShouldWorkFine() throws FieldException {
        testABean=new TestABean(BeanWithPrimitiveArrays.class);
        testABean.test();
    }

    public static class BeanWithPrimitiveArrays {
        private int[] ints;

        public int[] getInts() {
            return ints;
        }

        public void setInts(int[] ints) {
            this.ints = ints;
        }
    }

    public static class BeanWithArrays {
        private String[] strings;
        private Short[] shorts;
        private Byte[] bytes;
        private Character[] chars;
        private Integer[] ints;
        private Float[] floats;
        private Long[] longs;
        private Double[] doubles;
        private Boolean[] booleans;
        private Date[] dates;

        public String[] getStrings() {
            return strings;
        }

        public void setStrings(String[] strings) {
            this.strings = strings;
        }

        public Short[] getShorts() {
            return shorts;
        }

        public void setShorts(Short[] shorts) {
            this.shorts = shorts;
        }

        public Byte[] getBytes() {
            return bytes;
        }

        public void setBytes(Byte[] bytes) {
            this.bytes = bytes;
        }

        public Character[] getChars() {
            return chars;
        }

        public void setChars(Character[] chars) {
            this.chars = chars;
        }

        public Integer[] getInts() {
            return ints;
        }

        public void setInts(Integer[] ints) {
            this.ints = ints;
        }

        public Float[] getFloats() {
            return floats;
        }

        public void setFloats(Float[] floats) {
            this.floats = floats;
        }

        public Long[] getLongs() {
            return longs;
        }

        public void setLongs(Long[] longs) {
            this.longs = longs;
        }

        public Double[] getDoubles() {
            return doubles;
        }

        public void setDoubles(Double[] doubles) {
            this.doubles = doubles;
        }

        public Boolean[] getBooleans() {
            return booleans;
        }

        public void setBooleans(Boolean[] booleans) {
            this.booleans = booleans;
        }

        public Date[] getDates() {
            return dates;
        }

        public void setDates(Date[] dates) {
            this.dates = dates;
        }
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