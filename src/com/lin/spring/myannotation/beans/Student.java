package com.lin.spring.myannotation.beans;

import com.lin.spring.myannotation.MyAnnotation;

/**
 * Created by lin on 2017/11/12.
 */

@MyAnnotation("laowang")
public class Student {

    public Student(){
        System.out.println("Student");
    }

    public Student(String name){
        System.out.println("Student :"+name);
    }

    public void hello(){
        System.out.println("hello student !");
    }
    public void sayHello(String name){
        System.out.print(name+" say hello!");
    }
}
