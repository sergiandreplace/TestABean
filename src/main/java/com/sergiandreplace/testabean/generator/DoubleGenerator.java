package com.sergiandreplace.testabean.generator;

import com.sergiandreplace.testabean.annotation.TargetClass;

import java.util.Random;

@TargetClass({Double.class, double.class})
public class DoubleGenerator implements Generator<Double> {


    private static final Random rand = new Random(System.currentTimeMillis());


    @Override
    public Double next() {
        return rand.nextDouble();

    }


}
