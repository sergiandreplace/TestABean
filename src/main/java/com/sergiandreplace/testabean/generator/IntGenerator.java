package com.sergiandreplace.testabean.generator;

import com.sergiandreplace.testabean.annotation.TargetClass;

import java.util.Random;

@TargetClass({Integer.class, int.class})
public class IntGenerator implements Generator<Integer> {

    private static final Random rand = new Random(System.currentTimeMillis());

    @Override
    public Integer next() {
        return rand.nextInt();

    }

}
