package com.lin.bigdata.thread;

import java.util.ArrayList;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by lin on 2017/11/7.
 */
public class LockTest {

    private static ArrayList<Integer> list=new ArrayList<>();
    static Lock lock=new ReentrantLock();
    public static void main(String args[]){



        new Thread("thread1"){

            @Override
            public void run() {
                Thread thread= Thread.currentThread();
                lock.lock();
                try {

                System.out.println(thread.getName()+" 获得锁！");
                for (int i=0;i<5;i++){
                 list.add(i);
                }
                }catch (Exception e){

                }finally {
                    lock.unlock();
                    System.out.println(thread.getName()+" 释放锁！");
                }
            }
        }.start();

        new Thread("thread2"){
            @Override
            public void run() {
                Thread thread= Thread.currentThread();
                lock.lock();
                try {

                    System.out.println(thread.getName()+" 获得锁！");
                    for (int j=6;j<10;j++){
                        list.add(j);
                    }
                }catch (Exception e){
                }finally {
                    lock.unlock();
                    System.out.println(thread.getName()+" 释放锁！");
                }
            }
        }.start();

        for (int i=0;i<list.size();i++){
            System.out.println(list.get(i));
        }

    }
}
