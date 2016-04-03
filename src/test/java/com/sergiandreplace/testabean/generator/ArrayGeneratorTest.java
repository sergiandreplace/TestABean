package com.sergiandreplace.testabean.generator;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class ArrayGeneratorTest {

    private GeneratorFactory generatorFactory;

    @Before
    public void setUp() {
        generatorFactory = new GeneratorFactory();
    }

    @Test
    public void whenStringArrayRequested_ShouldReturnStringArray() {
        ArrayGenerator stringArrayGenerator = new ArrayGenerator(String.class, generatorFactory);
        assertEquals(stringArrayGenerator.next().getClass(), String[].class);
    }

    @Test
    public void whenStringArrayRequested_ShouldBeNotEmpty() {
        ArrayGenerator stringArrayGenerator = new ArrayGenerator(String.class, generatorFactory);
        String[] result = (String[]) stringArrayGenerator.next();
        assertNotEquals(result.length, 0);
    }

    @Test
    public void whenStringArrayIsRequested_ShouldStringsShouldNotBeEmpty() {
        ArrayGenerator stringArrayGenerator = new ArrayGenerator(String.class, generatorFactory);
        String[] result = (String[]) stringArrayGenerator.next();
        for (String item : result) {
            assertFalse(StringUtils.isEmpty(item));
        }
    }

    @Test
    public void whenLimitsSetForSize_ShouldReturnCorrectSizes() {
        int minsize = 10;
        int maxsize = 1000;
        ArrayGenerator stringArrayGenerator = new ArrayGenerator(String.class, generatorFactory, minsize, maxsize);
        for (int i=0;i<1000;i++) {
            String[] result = (String[]) stringArrayGenerator.next();
            for (String item : result) {
                assertTrue(item.length() >= minsize && item.length() <= maxsize);
            }
        }

    }
}