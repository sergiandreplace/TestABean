package com.sergiandreplace.testabean.generator;

import com.sergiandreplace.testabean.annotation.TargetClass;

import java.util.Random;

@TargetClass({Float.class, float.class})
public class FloatGenerator implements Generator<Float> {


    private static final Random rand = new Random(System.currentTimeMillis());


    @Override
    public Float next() {
        return rand.nextFloat();

    }


}
