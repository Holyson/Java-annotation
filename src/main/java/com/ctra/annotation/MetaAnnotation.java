package com.ctra.annotation;
import sun.awt.SunHints;

import java.lang.annotation.*;
/*
* 四个元注解
* @Target
* @Retention
* @Document
* @Inherited
* */


// 测试   元注解
@MyFirstAnnotation
public class MetaAnnotation {

    public void test(){

    }
}



//这里由于需要定义多个元注解，就省略 public 都放在一个类下
// ===================================================

// Target 表示我们的注解可以用在哪些方法
//@Target( value = ElementType.METHOD) //作用域：方法
@Target( value ={ElementType.METHOD,ElementType.TYPE})

// @Retention  表示我们的注解在什么地方还有效
@Retention(value = RetentionPolicy.RUNTIME) // RUNTIME 顶级

// Inherited 子类可以继承父类的注解
@Inherited
@interface MyFirstAnnotation{

}