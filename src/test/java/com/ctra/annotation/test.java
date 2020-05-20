package com.ctra.annotation;

public class test {
    public static void main(String[] args) {
//        testInsideAnnotation();
        testCustomAnnotation();
    }

    // 测试：3个内置注解
    static void testInsideAnnotation(){
        InsideAnnotation ia = new InsideAnnotation();
        System.out.println(ia.toString());
        System.out.println(ia.age());
    }



    // 测试：自定义注解
    @CustomAnnotation(name="zhangsan",nums={1,2})
    static void testCustomAnnotation(){

    }

    // 测试：参数value注解
    @DefaultAnnotation("test")
    static void testDefaultAnnotation(){

    }
}
