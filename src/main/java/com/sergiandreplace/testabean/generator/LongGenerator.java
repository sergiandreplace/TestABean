package com.sergiandreplace.testabean.generator;

import com.sergiandreplace.testabean.annotation.TargetClass;

import java.util.Random;

@TargetClass({Long.class, long.class})
public class LongGenerator implements Generator<Long> {


    private static final Random rand=new Random(System.currentTimeMillis());


    @Override
    public Long next() {
        return rand.nextLong();

    }


}
