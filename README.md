#TestABean [![Build Status](https://travis-ci.org/sergiandreplace/TestABean.svg?branch=master)](https://travis-ci.org/sergiandreplace/TestABean) [![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-TestABean-brightgreen.svg?style=flat)](http://android-arsenal.com/details/1/3345)

"Tired of testing beans (aka: objects with just gettes/setters and no logic)? Do not test them!"

"But,but,but... They are code, so they need to be tested."

"So test them! But you don't have to write the tests by yourself!"

"But, but, but... how?"

"I'm happy you made that question..."

TestABean is a library intended to help you perform basic testing on plain data objects (or Beans to generalize).

## Usage 

Just execute:

``` Java
new TestABean(MyBean.class).test();
```

It will check the following:

* All the declared fields should have a getter and a setter (or a "is" method for getter in case of booleans)
* All the declared fields can perform a set and then retrieve the same value with a get (equals used)
* All the declared fields support transitive property. So, after:

``` Java
a.setValue(x);
b.setValue(a.getValue());
c.setValue(b.getValue());
```

The following should be true

``` Java
c.getValue().equals(x);
```

The retrieval and transivite checks are run 100 times each by default on each field with different random values.

## Adding it to your project

Gradle it!

```Groovy
    testCompile 'com.sergiandreplace.testabean:testabean:1.0.1'
```

## Configuration

When instancing your TestABean object, you can provide a Configuration object with different parameters:

``` Java
Configuration configuration=new Configuration.Builder()
    .setGeneratorFactory(generatorFactory)  // Uses a different GeneratorFactory
                                            // See later for further discussion
    .setExceptions("readOnlyField") // Sets a list of fields to not check (readonly, with logic, etc)
    .setTestingRepetitions(50) // Sets the number of times tests will be repeated. 100 by default.
    .build();
new TestABean(MyBean.class, configuration).test();
```

## Generators

Generators are responsible for creating new random values to test getters and setters. By default, generators are made for primitives and their boxed classes. 
In order to create a new one, you must extend the class Generator<T> and use the annotation @TargetClass to define which classes will be affected by this generator
(ex: IntGenerator targets both Integer and int); then, add your generator to a GeneratorFactory instance and supply it via constructor;

## Exceptions

In some cases, there are fields we don't want to check for many reasons (read only fields, complex data types, etc). If this is the case, you can
make TestABuild ignore them. You can do it in two ways:

* With the setExceptions method of the Configuration Builder
* Annotating your field with @NotABeanField

## TODO

This is just an initial version and there is room for a lot of improvement. These are some things in the roadmap:

* Ability to inject new checks
* Recursive check for nested classes
* Increase configuration for more flexible tests

## License

```

Copyright 2016 Sergi Martínez

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
