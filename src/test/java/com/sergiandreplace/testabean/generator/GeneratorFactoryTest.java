package com.sergiandreplace.testabean.generator;

import org.junit.Before;
import org.junit.Test;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class GeneratorFactoryTest {

    private GeneratorFactory generatorFactory;

    @Before
    public void setUp() {
        generatorFactory = new GeneratorFactory();
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenAddedGeneratorWithoutAnnotation_ShouldRaiseException() {
        generatorFactory.add(new Generator() {
            @Override
            public Object next() {
                return null;
            }
        });
    }

    @Test
    public void whenRequestStringGenerator_ShouldReturnString() {
        Generator generator=generatorFactory.get(String.class);
        assertThat(generator.next()).isInstanceOf(String.class);
    }

    @Test
    public void whenRequestStringArrayGenerator_ShouldReturnStringArray() {
        Generator generator=generatorFactory.get(String[].class);
        assertThat(generator.next().getClass().isArray());
        assertThat(generator.next().getClass().getComponentType()).isEqualTo(String.class);
    }

    @Test
    public void whenRequestBooleanGenerator_ShouldReturnBoolean () {
        Generator generator=generatorFactory.get(Boolean.class);
        assertThat(generator.next()).isInstanceOf(Boolean.class);
    }

    @Test
    public void whenRequestBooleanPrimitiveGenerator_ShouldReturnBoolean() {
        Generator generator=generatorFactory.get(boolean.class);
        assertThat(generator.next()).isInstanceOf(Boolean.class);
    }

    @Test
    public void whenRequestBooleanArray_ShouldReturnBooleanArray() {
        Generator generator=generatorFactory.get(Boolean[].class);
        assertThat(generator.next().getClass().isArray());
        assertThat(generator.next().getClass().getComponentType()).isEqualTo(Boolean.class);
    }

    @Test
    public void whenRequestDoubleGenerator_ShouldReturnDouble() {
        Generator generator=generatorFactory.get(Double.class);
        assertThat(generator.next()).isInstanceOf(Double.class);
    }

    @Test
    public void whenRequestDoublePrimitiveGenerator_ShouldReturnDouble() {
        Generator generator=generatorFactory.get(double.class);
        assertThat(generator.next()).isInstanceOf(Double.class);
    }

    @Test
    public void whenRequestDoubleArray_ShouldReturnDoubleArray() {
        Generator generator=generatorFactory.get(Double[].class);
        assertThat(generator.next().getClass().isArray());
        assertThat(generator.next().getClass().getComponentType()).isEqualTo(Double.class);
    }


    @Test
    public void whenRequestFloatGenerator_ShouldReturnFloat() {
        Generator generator=generatorFactory.get(Float.class);
        assertThat(generator.next()).isInstanceOf(Float.class);
    }

    @Test
    public void whenRequestFloatPrimitiveGenerator_ShouldReturnFloat() {
        Generator generator=generatorFactory.get(float.class);
        assertThat(generator.next()).isInstanceOf(Float.class);
    }

    @Test
    public void whenRequestFloatArray_ShouldReturnFloatArray() {
        Generator generator=generatorFactory.get(Float[].class);
        assertThat(generator.next().getClass().isArray());
        assertThat(generator.next().getClass().getComponentType()).isEqualTo(Float.class);
    }


    @Test
    public void whenRequestIntegerGenerator_ShouldReturnInteger() {
        Generator generator=generatorFactory.get(Integer.class);
        assertThat(generator.next()).isInstanceOf(Integer.class);
    }

    @Test
    public void whenRequestIntPrimitiveGenerator_ShouldReturnInteger() {
        Generator generator=generatorFactory.get(int.class);
        assertThat(generator.next()).isInstanceOf(Integer.class);
    }

    @Test
    public void whenRequestIntegerArray_ShouldReturnIntegerArray() {
        Generator generator=generatorFactory.get(int[].class);
        assertThat(generator.next().getClass().isArray());
        assertThat(generator.next().getClass().getComponentType()).isEqualTo(int.class);
    }

    @Test
    public void whenRequestCharArrayGenerator_ShouldReturnString() {
        Generator generator=generatorFactory.get(char[].class);
        assertThat(generator.next().getClass().isArray());
        assertThat(generator.next().getClass().getComponentType()).isEqualTo(char.class);
    }

    @Test
    public void whenRequestLongGenerator_ShouldReturnLong() {
        Generator generator=generatorFactory.get(Long.class);
        assertThat(generator.next()).isInstanceOf(Long.class);
    }

    @Test
    public void whenRequestLongPrimitiveGenerator_ShouldReturnLong() {
        Generator generator=generatorFactory.get(long.class);
        assertThat(generator.next()).isInstanceOf(Long.class);
    }

    @Test
    public void whenRequestLongArray_ShouldReturnLongArray() {
        Generator generator=generatorFactory.get(Long[].class);
        assertThat(generator.next().getClass().isArray());
        assertThat(generator.next().getClass().getComponentType()).isEqualTo(Long.class);
    }


    @Test
    public void whenRequestShortGenerator_ShouldReturnShort() {
        Generator generator=generatorFactory.get(Short.class);
        assertThat(generator.next()).isInstanceOf(Short.class);
    }

    @Test
    public void whenRequestShortPrimitiveGenerator_ShouldReturnShort() {
        Generator generator=generatorFactory.get(short.class);
        assertThat(generator.next()).isInstanceOf(Short.class);
    }

    @Test
    public void whenRequestShortArray_ShouldReturnShortArray() {
        Generator generator=generatorFactory.get(Short[].class);
        assertThat(generator.next().getClass().isArray());
        assertThat(generator.next().getClass().getComponentType()).isEqualTo(Short.class);
    }



    @Test
    public void whenRequestByteGenerator_ShouldReturnByte() {
        Generator generator=generatorFactory.get(Byte.class);
        assertThat(generator.next()).isInstanceOf(Byte.class);
    }

    @Test
    public void whenRequestBytePrimitiveGenerator_ShouldReturnByte() {
        Generator generator=generatorFactory.get(byte.class);
        assertThat(generator.next()).isInstanceOf(Byte.class);
    }

    @Test
    public void whenRequestByteArray_ShouldReturnByteArray() {
        Generator generator=generatorFactory.get(Byte[].class);
        assertThat(generator.next().getClass().isArray());
        assertThat(generator.next().getClass().getComponentType()).isEqualTo(Byte.class);
    }


    @Test
    public void whenRequestDateGenerator_ShouldReturnDate() {
        Generator generator=generatorFactory.get(Date.class);
        assertThat(generator.next()).isInstanceOf(Date.class);
    }

    @Test
    public void whenRequestDateArray_ShouldReturnDateArray() {
        Generator generator=generatorFactory.get(Date[].class);
        assertThat(generator.next().getClass().isArray());
        assertThat(generator.next().getClass().getComponentType()).isEqualTo(Date.class);
    }


    @Test
    public void whenRequestListGenerator_ShouldReturnList() {
        List<String> list=new ArrayList<>();
        Generator generator=generatorFactory.get(AbstractList.class);
        assertThat(generator.next()).getClass().isAssignableFrom(AbstractList.class);
    }

    @Test
    public void whenRequestListArray_ShouldReturnBooleanArray() {
        Generator generator=generatorFactory.get(AbstractList[].class);
        assertThat(generator.next().getClass().isArray());
        assertThat(generator.next().getClass().getComponentType().isAssignableFrom(AbstractList.class)).isTrue();
    }








}