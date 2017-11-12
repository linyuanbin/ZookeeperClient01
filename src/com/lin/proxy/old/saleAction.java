package com.lin.proxy.old;

import org.junit.Test;

/**
 * Created by lin on 2017/11/9.
 */
public class saleAction {

    @Test
    public void saleByBoss(){
        IBoss boss=new Boss();
        System.out.println("");
        int money=boss.yifu("xl");
        System.out.println("价格："+money+"  ame="+boss.name("乔丹"));
    }
}
