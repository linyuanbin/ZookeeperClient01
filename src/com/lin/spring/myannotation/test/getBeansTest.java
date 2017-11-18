package com.lin.spring.myannotation.test;

import com.lin.spring.myannotation.MyAnnotation;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by lin on 2017/11/12.
 */

@Component  //使用注解是实现bean配置
public class getBeansTest implements ApplicationContextAware{

    public static void main(String args[]){

        ClassPathXmlApplicationContext ctx=new ClassPathXmlApplicationContext("com/lin/spring/config/spring02.xml");

    }

    //在这个类被实例化时，这个方法会执行
    @Override
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {

        //根据注解得到对象
        Map<String,Object> serviceBeanMap=ctx.getBeansWithAnnotation(MyAnnotation.class); //扫描包，将用了MyAnnotation注解的对象全部实例化并加载过来

        for (String s:serviceBeanMap.keySet()){ //得到的是注解传人的value
            System.out.println("key="+s);
        }

        for(Object serviceBean:serviceBeanMap.values()){
            try {
            //获取自定义注解上的值
                String value=serviceBean.getClass().getAnnotation(MyAnnotation.class).value();
                System.out.println("value="+value);


                //反射被注解类，并调用指定方法
                Method  method=serviceBean.getClass().getMethod("hello"); //获取方法 无参数
                Object object=method.invoke(serviceBean); //执行方法

                Method  method2=serviceBean.getClass().getMethod("sayHello",String.class); //获取方法 有个一String 类型参数
                Object object2=method2.invoke(serviceBean,"beiHua University"); //执行方法

                Field field=serviceBean.getClass().getField("name");
                System.out.println("field"+field);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }
}
