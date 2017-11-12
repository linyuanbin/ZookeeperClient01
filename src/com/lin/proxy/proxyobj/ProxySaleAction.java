package com.lin.proxy.proxyobj;

import com.lin.proxy.old.Boss;
import com.lin.proxy.old.IBoss;
import org.apache.log4j.xml.XMLLayout;
import org.junit.Test;

/**
 * Created by lin on 2017/11/9.
 */
public class ProxySaleAction {

    @Test
    public void saleAction(){
        ProxyBoss proxy=new ProxyBoss();//获取代理对象
        IBoss boss=proxy.getProxy(10,IBoss.class, Boss.class);
        int money= boss.yifu("xxL");
        System.out.println("代理：价格="+money);
    }

    @Test
    public void saleAction2(){
        ProxyBoss proxy=new ProxyBoss();//获取代理对象
        IBoss boss=proxy.getProxy2("乔丹",IBoss.class, Boss.class);
        String name= boss.name("限量版");
        System.out.println("代理：name="+name);
    }

}
