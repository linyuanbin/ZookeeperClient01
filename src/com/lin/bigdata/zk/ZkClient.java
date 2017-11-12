package com.lin.bigdata.zk;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;
import org.junit.internal.runners.statements.FailOnTimeout;

import java.io.IOException;
import java.util.List;

/**
 * Created by lin on 2017/11/4.
 */
public class ZkClient {

    private static final String connectionString="192.168.0.210:2181,192.168.0.211:2181,192.168.0.212:2181";
    private static final int sessionTimeout=2000;

    ZooKeeper zkClient=null;

    @Before
    public void init() throws IOException {
        zkClient=new ZooKeeper(connectionString, sessionTimeout,new Watcher(){
            @Override
            public void process(WatchedEvent event) {
                //对zoookeeper返回的事件做出响应 就是对zookeeper中的节点进行监听
                System.out.println(event.getType()+"---"+event.getPath());

                //由于zookeeper中wanch监听只会监听一次，要实现持续监听得嵌套调用
                try {
                    zkClient.getChildren("/",true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    //创建子节点到zk中
     public void testCreateNode() throws KeeperException, InterruptedException {

         //参数1、节点路径   2.节点数据   3.节点权限    4.节点类型     返回值是返回成功创建的节点的路径
         String nodePath=zkClient.create("/eclipse","helllo".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
         //上传的数据可以是任何类型，但都要转成byte类型
     }


    //获取zk节点数据
    @Test
    public void getData() throws KeeperException, InterruptedException {

        byte[] data = zkClient.getData("/eclipse", false, null);
        System.out.println(new String(data));
    }

    //删除节点
    @Test
    public void deleteZNode() throws KeeperException, InterruptedException {
            //第二个参数是要删除的版本   -1 代表删除所有版本
         zkClient.delete("/lin",-1);

    }

    //修改数据
    @Test
    public void setData() throws KeeperException, InterruptedException {
        zkClient.setData("/eclipse","hello zookeeper".getBytes(),-1);
        byte[] data = zkClient.getData("/eclipse", false, null);
        System.out.println(new String(data));

    }


    //判断子节点是否存在
    @Test
    public void testExists(){
        try {
            Stat stat = zkClient.exists("/lin", false);
            System.out.println(stat==null?"不存在":"存在");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //监听
    @Test
    public void getChildren() throws KeeperException, InterruptedException {
        List<String> children = zkClient.getChildren("/", true);
        for(String chil:children){
            System.out.println(chil);
        }

        //要实现始终处于监听状态得让程序始终处于运行状态：
        Thread.sleep(Long.MAX_VALUE);
    }


}
