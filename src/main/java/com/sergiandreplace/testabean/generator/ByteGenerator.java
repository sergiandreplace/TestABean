package com.sergiandreplace.testabean.generator;

import com.sergiandreplace.testabean.annotation.TargetClass;

import java.util.Random;

@TargetClass({Byte.class, byte.class})
public class ByteGenerator implements Generator<Byte> {


    private static final Random rand=new Random(System.currentTimeMillis());


    @Override
    public Byte next() {
        byte[] bytes = new byte[1];
        rand.nextBytes(bytes);
        return bytes[0];

    }


}
