package com.sergiandreplace.testabean.generator;

import com.sergiandreplace.testabean.annotation.TargetClass;

import java.util.Random;

@TargetClass({Boolean.class, boolean.class})
public class BooleanGenerator implements Generator<Boolean> {


    private static final Random rand = new Random(System.currentTimeMillis());


    @Override
    public Boolean next() {
        return rand.nextBoolean();

    }


}
