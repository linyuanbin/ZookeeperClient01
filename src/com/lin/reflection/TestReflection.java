package com.lin.reflection;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static java.lang.Class.forName;

/**
 * Created by lin on 2017/11/8.
 * java 的放射机制
 * 三种获取类方式
 */
public class TestReflection {


    public static void main(String args[]) throws Exception {

        //java放射机制中三种获取class的方式
        //1通过全路径获取类
        Class aClass=null;
        aClass= Class.forName("com.lin.reflection.Person");
        System.out.println("1通过全路径获取类: " + aClass);



        //2 通过对象的getClass()方法获取,这个使用的少（一般是传的是Object，不知道是什么类型的时候才用）
        Person person=new Person();
        Object obj=person.getClass();
        aClass=obj.getClass();
        System.out.println("通过对象的getClass(): " + aClass);


        //3 直接通过类名.Class的方式得到
        aClass = Person.class;
        System.out.println("通过类名: " + aClass);


        //利用newInstance创建对象：调用的类必须有无参的构造器
        //利用newInstance创建Person类对象实质就是利用Person 的无参数构造方法创建对象
        aClass= Class.forName("com.lin.reflection.Person");
        Object obj2=aClass.newInstance();
        System.out.println("利用newInstance创建对象: " + obj2);

        //获取成员方法
        Method method1=aClass.getMethod("publicMethod");
        method1.invoke(obj2); //执行publicMethod()

        //获取私有方法
        Object obj3=aClass.newInstance();
        Method method2=aClass.getDeclaredMethod("privateMethod");
        method2.setAccessible(true); //设置可见
        method2.invoke(obj3);

        //私有带参数的方法
        aClass= Class.forName("com.lin.reflection.Person");
        Method method3=aClass.getDeclaredMethod("setEl",int.class,String.class);//获取带参数的
        method3.setAccessible(true); //对私有的方法设置可见
        Object obj4=aClass.newInstance();
        method3.invoke(obj4,12,"laowang"); //第一个参数是对象，后两个是函数的参数


        //访问带参数的方法
        aClass= Class.forName("com.lin.reflection.Person");
        Method method4=aClass.getMethod("SetElp",int.class,String.class);//获取带参数的
        Object obj5=aClass.newInstance();
        method4.invoke(obj5,15,"laoli"); //第一个参数是对象，后两个是函数的参数
        System.out.println(((Person) obj5).getAge()+"   "+((Person) obj5).getName());

        //获取公有成员变量
        Field field=aClass.getField("age");
        int age=field.getInt(obj5);
        System.out.println("age="+age);


        //获取私有变量 String 类型 需要通过获取改属性的getter方法再去获取改属性
        Field fil=aClass.getDeclaredField("name");
        fil.setAccessible(true); //因为是private类型，需要设置可见才能获取
        System.out.println(fil.getName());
        Method method05=aClass.getMethod("get"+getMethodName(fil.getName()));
        String name=(String) method05.invoke(obj5);
        System.out.println("name="+name);

    }
    private static String getMethodName(String fildeName) throws Exception{
        byte[] items = fildeName.getBytes();
        items[0] = (byte) ((char) items[0] - 'a' + 'A');
        return new String(items);
    }
}
