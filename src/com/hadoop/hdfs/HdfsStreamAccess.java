package com.hadoop.hdfs;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by lin on 2017/11/22.
 */

//通过流的方式实现文件上传下载
public class HdfsStreamAccess {

    FileSystem fs=null;

    @Before
    public void init() throws IOException, URISyntaxException, InterruptedException {

        Configuration conf=new Configuration();

        conf.set("dfs.replication","2");//设置上传的文件在hdfs文件系统的中副本数量，默认值是3份

        //1.拿到文件系统操作对象
        /*conf.set("fs.defaultFS","hdfs://192.168.0.210:9000");
         fs = FileSystem.get(conf);*/

        //2.可以直接传人uri和用户身份
        fs=FileSystem.get(new URI("hdfs://192.168.0.210:9000"),conf,"hadoop");
    }


    //流形式上传文件
    @Test
    public void testUpload() throws Exception {

        FSDataOutputStream out = fs.create(new Path("/baby.love"), true);
        FileInputStream in=new FileInputStream("D:/baby.love");
        IOUtils.copy(in,out);//将输入流复制到输出流中   import org.apache.commons.io.IOUtils;

    }

    @Test
    public void testDownload() throws Exception {

        FSDataInputStream in =fs.open(new Path("/baby.love"));
        //in.seek(12);//从12字节开始读
        FileOutputStream out=new FileOutputStream("d:/babyCP.love");
        IOUtils.copy(in,out); //import org.apache.commons.io.IOUtils;

    }

}
