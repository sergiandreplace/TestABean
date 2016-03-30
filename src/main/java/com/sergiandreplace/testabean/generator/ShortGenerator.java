package com.sergiandreplace.testabean.generator;

import com.sergiandreplace.testabean.annotation.TargetClass;

import java.util.Random;

@TargetClass({Short.class, short.class})
public class ShortGenerator implements Generator<Short> {


    private static final Random rand=new Random(System.currentTimeMillis());


    @Override
    public Short next() {
        return  (short) (rand.nextInt(Short.MAX_VALUE - Short.MIN_VALUE + 1) + Short.MIN_VALUE);

    }


}
