package com.sergiandreplace.testabean.generator;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class GeneratorFactoryTest {

    private GeneratorFactory generatorFactory;

    @Before
    public void setUp() {
        generatorFactory = new GeneratorFactory();
    }

    @Test
    public void whenRequestGenerator_ShouldReturnCorrectValue() {
        Generator generator=generatorFactory.get(String.class);
        assertThat(generator.next()).isInstanceOf(String.class);
    }


}