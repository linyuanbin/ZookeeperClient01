package com.hadoop.hdfs;


import org.apache.commons.collections.FastArrayList;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by lin on 2017/11/18.
 *在windows环境下运行该程序需要配置hadoop环境变量
 *
 * 客户端操作hdfs文件系统是有一个用户身份的
 *默认情况下  hdfs客户端api会从jvm中获取一个参数作为自己的用户身份（如：Main函数参数的 args[0]） -DHADOOP_USER_NAME=hadoop
 *
 *也可以在创建文件系统对象时传入用户身份
 */
public class HdfsClientDemo {

    FileSystem fs=null;

    @Before
    public void init() throws IOException, URISyntaxException, InterruptedException {

        Configuration conf=new Configuration();


        //1.拿到文件系统操作对象
        /*conf.set("fs.defaultFS","hdfs://192.168.0.210:9000");
         fs = FileSystem.get(conf);*/

        //2.可以直接传人uri和用户身份
        fs=FileSystem.get(new URI("hdfs://192.168.0.210:9000"),conf,"hadoop");

    }

    @Test
    public void testUploard() throws IOException {  //文件上传到hdfs系统 / 根
        fs.copyFromLocalFile(new Path("E:\\upload.txt"),new Path("/test_upload.txt.bak"));
        fs.close();
    }

    @Test
    public void testDownload() throws IOException { //文件下载
        fs.copyToLocalFile(false,new Path("/test_upload.txt.bak"),new Path("D:/"),true); //下载到D盘根目录
        //fs.copyToLocalFile(new Path("/test_upload.txt.bak"),new Path("D:/"));
        fs.close();
    }


}
