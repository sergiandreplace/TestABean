package com.sergiandreplace.testabean.generator;


import java.lang.reflect.Array;
import java.util.Random;

public class ArrayGenerator implements Generator<Object> {

    private final Class clazz;
    private final GeneratorFactory generatorFactory;
    private int maxLenght=1000;
    private int minLength=100;
    private Random random=new Random(System.currentTimeMillis());

    public ArrayGenerator(Class clazz, GeneratorFactory generatorFactory) {
        this.clazz=clazz;;
        this.generatorFactory = generatorFactory;
    }

    public ArrayGenerator(Class clazz, GeneratorFactory generatorFactory, int minLenght, int maxLength) {
        this.clazz=clazz;
        this.generatorFactory = generatorFactory;
        this.minLength=minLenght;
        this.maxLenght=maxLength;
    }

    @Override
    public Object next() {
        int size=random.nextInt(maxLenght-minLength)+minLength;

        Generator generator=generatorFactory.get(clazz);
        Object array=  Array.newInstance(clazz, size);
        for (int i = 0; i<size;i++) {
            Array.set(array,i, generator.next());
        }
        return array;
    }




}
