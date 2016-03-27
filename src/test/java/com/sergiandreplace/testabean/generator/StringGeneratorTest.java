package com.sergiandreplace.testabean.generator;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class StringGeneratorTest {

    public static final int MAX_LENGTH = 150;
    public static final int MIN_LENGTH = 0;
    private StringGenerator stringGenerator;

    @Before
    public void setUp() {
        stringGenerator = new StringGenerator(MIN_LENGTH, MAX_LENGTH);
    }

    @Test
    public void whenExecuted_shouldReturnValidStrings() {
        int minSize=MAX_LENGTH;
        int maxSize=MIN_LENGTH;
        int currentSize=0;
        for (int i = 0; i < 100; i++) {
            String value = stringGenerator.next();
            currentSize=value.length();
            if (currentSize<minSize) {
                minSize=currentSize;
            }
            if (currentSize>maxSize) {
                maxSize=currentSize;
            }
        }
        assertThat(currentSize).isBetween(MIN_LENGTH,MAX_LENGTH);
    }

}