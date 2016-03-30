package com.sergiandreplace.testabean.generator;

import com.sergiandreplace.testabean.annotation.TargetClass;

import java.util.Random;

@TargetClass({Byte[].class, byte[].class})
public class ByteArrayGenerator implements Generator<byte[]> {


    private static final Random rand = new Random(System.currentTimeMillis());


    @Override
    public byte[] next() {
        int size = rand.nextInt();
        byte[] array = new byte[size];
        rand.nextBytes(array);
        return array;

    }


}
