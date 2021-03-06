package com.sergiandreplace.testabean.generator;

import com.sergiandreplace.testabean.annotation.TargetClass;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

@TargetClass({
        List.class,
        AbstractList.class,
        AbstractSequentialList.class,
        ArrayList.class,
        CopyOnWriteArrayList.class,
        LinkedList.class,
        Stack.class,
        Vector.class
    })
public class ListGenerator implements Generator<List> {

    public ListGenerator() {

    }

    @Override
    public List next() {

        return new ArrayList();

    }


}
