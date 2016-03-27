package com.sergiandreplace.testabean;

import com.sergiandreplace.testabean.generator.GeneratorFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Configuration {
    private List<String> exceptions;
    private GeneratorFactory generatorFactory;
    private int testingRepetitions;

    private Configuration() {

    }

    public List<String> getExceptions() {
        return exceptions;
    }

    private void setExceptions(List<String> exceptions) {
        this.exceptions = exceptions;
    }

    public GeneratorFactory getGeneratorFactory() {
        return generatorFactory;
    }

    private void setGeneratorFactory(GeneratorFactory generatorFactory) {
        this.generatorFactory = generatorFactory;
    }

    public int getTestingRepetitions() {
        return testingRepetitions;
    }

    private void setTestingRepetitions(int testingRepetitions) {
        this.testingRepetitions = testingRepetitions;
    }

    public static class Builder {
        private List<String> exceptions;
        private GeneratorFactory generatorFactory;
        private int testingRepetitions=100;

        public Builder setExceptions(List<String> exceptions) {
            this.exceptions = exceptions;
            return this;
        }

        public Builder setExceptions(String... exceptions) {
            this.exceptions = Arrays.asList(exceptions);
            return this;
        }

        public Builder setGeneratorFactory(GeneratorFactory generatorFactory) {
            this.generatorFactory = generatorFactory;
            return this;
        }

        public Builder setTestingRepetitions(int repetitions) {
            this.testingRepetitions=repetitions;
            return this;
        }

        public Configuration build() {
            Configuration configuration = new Configuration();

            if (exceptions == null) {
                exceptions = new ArrayList<>();
            }
            configuration.setExceptions(exceptions);


            if (generatorFactory == null) {
                generatorFactory = new GeneratorFactory();
            }
            configuration.setGeneratorFactory(generatorFactory);
            configuration.setTestingRepetitions(testingRepetitions);
            return configuration;
        }
    }


}
