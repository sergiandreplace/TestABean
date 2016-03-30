package com.sergiandreplace.testabean.generator;

import com.sergiandreplace.testabean.annotation.TargetClass;

import java.util.HashMap;
import java.util.Map;


public class GeneratorFactory {
    private final Map<Class, Generator> generators;

    public GeneratorFactory() {
        generators = new HashMap<>();
        add(new StringGenerator());
        add(new IntGenerator());
        add(new LongGenerator());
        add(new BooleanGenerator());
        add(new FloatGenerator());
        add(new DoubleGenerator());
        add(new ByteArrayGenerator());

    }


    public void add(Generator generator) {
        TargetClass annotation = generator.getClass().getAnnotation(com.sergiandreplace.testabean.annotation.TargetClass.class);
        if (annotation == null) {
            throw new IllegalArgumentException("TargetClass annotation not found");
        }

        for (Class clazz : annotation.value()) {
            generators.put(clazz, generator);
        }
    }

    public Generator get(Class targetClass) {
        return generators.get(targetClass);
    }

    public Map<Class, Generator> getGenerators() {
        return generators;
    }
}
