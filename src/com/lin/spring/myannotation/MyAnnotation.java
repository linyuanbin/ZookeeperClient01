package com.lin.spring.myannotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by lin on 2017/11/12.
 */

/*
    自定义的注解 MyAnnotation
 */
    //这三个注解必须有，自定义注解才能正常使用
    @Target({ElementType.TYPE}) //注解用在接口上（类名上的注解）
    @Retention(RetentionPolicy.RUNTIME) //(生命周期)虚拟机将在运行期间保留注解，因此可以通过反射机制读取注解
    @Component
public @interface MyAnnotation {

    String value();//用于获取注解后面括号传入的值 "value" --->  @MyAnnotation("value")

}
