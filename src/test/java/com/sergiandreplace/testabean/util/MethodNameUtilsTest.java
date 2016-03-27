package com.sergiandreplace.testabean.util;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class MethodNameUtilsTest {


    @Test (expected = NullPointerException.class)
    public void whenCheckStringGetsNull_ShouldRaiseException() {
        MethodNameUtils.checkString(null);
    }

    @Test (expected = StringIndexOutOfBoundsException.class)
    public void whenCheckStringGetsEmptyString_ShouldRaiseException() {
        MethodNameUtils.checkString("");
    }

    @Test (expected = NullPointerException.class)
    public void whenUpperCamelCaseGetsNull_ShouldRaiseException() {
        MethodNameUtils.getUpperCamelCase(null);
    }

    @Test (expected = StringIndexOutOfBoundsException.class)
    public void whenUpperCamelCaseGetsEmptyString_ShouldRaiseException() {
        MethodNameUtils.getUpperCamelCase("");
    }

    @Test
    public void whenUpperCamelCaseGetsString_ShouldReturnCorrectValue() {
        assertThat(MethodNameUtils.getUpperCamelCase("fooBar")).isEqualTo("FooBar");
    }

    @Test
    public void whenUpperCamelCaseGetsStringStartingWithANumber_ShouldReturnCorrectValue() {
        assertThat(MethodNameUtils.getUpperCamelCase("1fooBar")).isEqualTo("1fooBar");
    }

    @Test
    public void whenFieldProvided_ShouldReturnGetter () {
        assertThat(MethodNameUtils.getFieldGetter("fooBar")).isEqualTo("getFooBar");
    }

    @Test
    public void whenBooleanFieldProvided_ShouldReturnIsGetter () {
        assertThat(MethodNameUtils.getFieldIsGetter("fooBar")).isEqualTo("isFooBar");
    }

    @Test
    public void whenFieldProvided_ShouldReturnSetter () {
        assertThat(MethodNameUtils.getFieldSetter("fooBar")).isEqualTo("setFooBar");
    }
}