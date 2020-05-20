package com.ctra.annotation;

import java.util.ArrayList;
import java.util.List;

/*
 * 三个内置注解
 * @Override
 * @Deprecated
 * @SuppressWarnings
 * */
//@SuppressWarnings("all")
public class InsideAnnotation extends Object {
    @Override
    public String toString() {
        return null;
    }

    @Deprecated
    public int age() {
        return 1;
    }

    @SuppressWarnings("all")
    public void arr() {
        List list = new ArrayList();
    }

}
