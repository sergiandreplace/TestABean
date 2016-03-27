package com.sergiandreplace.testabean;

import com.sergiandreplace.testabean.generator.GeneratorFactory;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class ConfigurationTest {

    private Configuration.Builder builder;

    @Before
    public void setUp() {
        builder = new Configuration.Builder();
    }

    @Test
    public void whenNotConfigured_ShouldReturnEmptyExceptionsList() {
        Configuration configuration = builder.build();
        assertThat(configuration.getExceptions()).hasSize(0);
    }

    @Test
    public void whenNotConfigured_ShouldReturnDefaultGeneratorFactory() {
        Configuration configuration = builder.build();
        assertThat(configuration.getGeneratorFactory().getGenerators().keySet()).hasSameElementsAs(new GeneratorFactory().getGenerators().keySet());
    }

    @Test
    public void whenNotConfigured_ShouldReturnDefaultTestingRepetitions() {
        Configuration configuration = builder.build();
        assertThat(configuration.getTestingRepetitions()).isEqualTo(100);
    }

    @Test
    public void whenExceptionsConfigured_ShouldReturnCorrectValue() {
        Configuration configuration = builder.setExceptions("one","two","three").build();
        assertThat(configuration.getExceptions()).containsExactly("one","two","three");
    }

    @Test
    public void whenGeneratorFactoryConfigured_ShouldReturnCorrectValue() {
        GeneratorFactory generatorFactory=new GeneratorFactory();
        Configuration configuration = builder.setGeneratorFactory(generatorFactory).build();
        assertThat(configuration.getGeneratorFactory()).isEqualTo(generatorFactory);

    }

    @Test
    public void whenTestingRepetitionsConfigured_ShouldReturnCorrectValue() {
        Configuration configuration = builder.setTestingRepetitions(1234).build();
        assertThat(configuration.getTestingRepetitions()).isEqualTo(1234);
    }

}