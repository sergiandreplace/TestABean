package com.sergiandreplace.testabean.generator;

import com.sergiandreplace.testabean.annotation.TargetClass;

import java.util.Random;

@TargetClass({char.class, Character.class})
public class CharGenerator implements Generator<Character> {


    private static final Random rand = new Random(System.currentTimeMillis());


    @Override
    public Character next() {
        return (char) rand.nextInt(2^16);

    }


}
