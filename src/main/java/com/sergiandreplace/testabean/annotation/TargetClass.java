package com.sergiandreplace.testabean.annotation;

import java.lang.annotation.*;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface TargetClass  {
    Class[] value() ;
}
