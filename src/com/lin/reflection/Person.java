package com.lin.reflection;

/**
 * Created by lin on 2017/11/8.
 */
public class Person {

    public  int age=0;
    private String name="null";

    public Person(){
        System.out.println("无参数构造！");
    }

    public Person(int age ,String name){
        this.age=age;
        this.name=name;
    }

    public void publicMethod(){
        System.out.println("public Method!");
    }

    public void SetElp(int age,String name){
        System.out.println(age+"  "+name);
        this.age=age;
        this.name=name;
    }

    private void setEl(int age,String name){
        System.out.println(age+"  "+name);
        this.age=age;
        this.name=name;
    }

    private void privateMethod(){
        System.out.println("private Method!");
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
