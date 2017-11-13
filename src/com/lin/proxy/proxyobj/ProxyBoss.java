package com.lin.proxy.proxyobj;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by lin on 2017/11/9.
 */

//做代理的类，对已有的业务逻辑接口实现类的功能进行扩展，这样就不需要去修改原有的实现类中的方法了
public class ProxyBoss {    //getProxy是自定义的，参数个数不限，不过一定要有interfaceClass 和 implementClass这两，因为后面需要用
    public static <T> T getProxy(final int disCountCoupon,final Class<?> interfaceClass,final Class<?> implementClass){
                        //disCountCoupon 代表代金卷   interfaceClass是旧的义务逻辑中的接口  implementClass是旧的业务逻辑中接口实现类
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Integer returnValue=(Integer)method.invoke(implementClass.newInstance(),args);//调用原始对象以后返回的值  implementClass.newInstance()得到Object对象
                System.out.println("method:"+method+" args"+args.length);
                return returnValue-disCountCoupon;//disCountCoupon相当于优惠劵，将原先的价格减去优惠劵金额
            }
        });
    }

    public static <T> T getProxy2(final String name2,final Class<?> interfaceClass,final Class<?> implementClass){
        //disCountCoupon 代表代金卷   interfaceClass是旧的义务逻辑中的接口  implementClass是旧的业务逻辑中接口实现类
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(),new Class[]{interfaceClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                String returnValue=(String)method.invoke(implementClass.newInstance(),args);//调用原始对象以后返回的值
                System.out.println("method:"+method+" args"+args.length);
                return returnValue+name2;//disCountCoupon相当于优惠劵，将原先的价格减去优惠劵金额
            }
        });
    }


}
