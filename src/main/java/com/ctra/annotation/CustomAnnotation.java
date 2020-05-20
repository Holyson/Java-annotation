package com.ctra.annotation;

/*
* 自定义注解
* */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;

@Retention(RetentionPolicy.RUNTIME) // 最大生命周期
@Target({ElementType.TYPE,ElementType.METHOD}) //  类与方法上都可以
public @interface CustomAnnotation {
    //定义注解的参数：参数类型+参数名()+;
    String name();
    String nameDefault() default "";
    int age() default 0;
    int id() default -1; // 如果默认值为-1，代表不存在
    String[] schools() default {"清华","北大"};
    int[] nums();
}
