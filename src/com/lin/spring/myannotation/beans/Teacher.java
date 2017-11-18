package com.lin.spring.myannotation.beans;

import com.lin.spring.myannotation.MyAnnotation;

/**
 * Created by lin on 2017/11/12.
 */

@MyAnnotation("Teacher")   //使用自定义的标签
public class Teacher {
    public String name="TeacherName";

    public Teacher(){
        System.out.println("Teacher");
    }

    public Teacher(String name){
        System.out.println("Teacher :"+name);
    }

    public void hello(){
        System.out.println("hello teacher!");
    }

    public void sayHello(String name){
        System.out.print(name+" say hello!");
    }
}
