package com.lin.bigdata.thread;

import javax.xml.stream.events.StartDocument;
import java.util.concurrent.locks.Lock;

/**
 * Created by  on 2017/11/7.
 */
public class MySynchronized {

    //多线程中的线程锁
    public static void main(String arge[]){

        final MySynchronized mySynchronized1=new MySynchronized();
        final MySynchronized mySynchronized2=new MySynchronized();

        new Thread(){

            @Override
            public void run() {

                synchronized (mySynchronized1){

                    System.out.println("thread1 start");
                    try {
                        //int n=10/0;
                        Thread.sleep(5000);
                        System.out.println("thread1 end");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
        new Thread(){

            @Override
            public void run() {

                //synchronized (mySynchronized1){  //争抢同一把锁时，线程1随眠，线程2需要等线程1结束才能执行 ，除非线程1出现异常
                synchronized (mySynchronized2){   //使用不同锁，线程1和2之间可以同时进行

                    System.out.println("thread2 start");

                        System.out.println("thread2 end");


                }

            }
        }.start();


    }



}
