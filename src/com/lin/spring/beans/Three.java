package com.lin.spring.beans;


import org.springframework.stereotype.Component;

/**
 * Created by lin on 2017/11/12.
 */
@Component()
public class Three {

    public Three(){
        System.out.println("Three");
    }

    public Three(String name){
        System.out.println("Three name:"+name);
    }

}

