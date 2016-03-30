package com.sergiandreplace.testabean.generator;

import org.junit.Before;
import org.junit.Test;


public class ShortGeneratorTest {

    public static final int MAX_LENGTH = 150;
    public static final int MIN_LENGTH = 0;
    private ShortGenerator shortGenerator;

    @Before
    public void setUp() {
        shortGenerator = new ShortGenerator();
    }

    @Test
    public void whenExecuted_shouldReturnValidStrings() {
        int minSize=MAX_LENGTH;
        int maxSize=MIN_LENGTH;
        int currentSize=0;
        for (int i = 0; i < 100; i++) {
            Short value = shortGenerator.next();
        }
    }

}