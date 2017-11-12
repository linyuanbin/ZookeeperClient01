package com.lin.proxy.old;

/**
 * Created by lin on 2017/11/9.
 */
//初始
public class Boss implements IBoss{


    @Override
    public int yifu(String size) {
        System.out.println("淘宝旗舰店 衣服型号"+size);
        return 50;
    }

    @Override
    public String name(String name) {

        return name; //返回衣服名字
    }


}
