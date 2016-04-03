package com.sergiandreplace.testabean.generator;

import com.sergiandreplace.testabean.annotation.TargetClass;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

@TargetClass({String.class})
public class StringGenerator implements Generator<String> {

    private static final Random rand = new Random(System.currentTimeMillis());
    private int minLength = 10;
    private int maxLength = 100;

    public StringGenerator() {

    }

    public StringGenerator(int minLength, int maxLength) {
        this.minLength = minLength;
        this.maxLength = maxLength;
    }

    @Override
    public String next() {
        int size = rand.nextInt(maxLength - minLength + 1) + minLength;
        return RandomStringUtils.randomAscii(size);

    }


}
