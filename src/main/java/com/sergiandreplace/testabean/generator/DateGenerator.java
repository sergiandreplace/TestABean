package com.sergiandreplace.testabean.generator;

import com.sergiandreplace.testabean.annotation.TargetClass;

import java.util.Date;
import java.util.Random;

@TargetClass({Date.class})
public class DateGenerator implements Generator<Date> {


    private static final Random rand = new Random(System.currentTimeMillis());


    public DateGenerator() {

    }



    @Override
    public Date next() {
        return new Date(rand.nextLong());
    }


}
