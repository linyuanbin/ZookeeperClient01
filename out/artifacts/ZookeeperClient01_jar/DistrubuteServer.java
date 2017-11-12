package com.lin.bigdata.zkdisc;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by lin on 2017/11/5.
 */
public class DistrubuteServer {

    private static final String connectionString="192.168.0.211:2181,192.168.0.210:2181,192.168.0.212:2181";
    private static final int sessionTimeout=2000;
    private static final String parentedNode="/servers";
    private ZooKeeper zk=null;


    public void getConnect() throws IOException, KeeperException, InterruptedException { //建立连接并监听

            zk=new ZooKeeper(connectionString, sessionTimeout,new Watcher(){
                @Override
                public void process(WatchedEvent event) {
                    //对zoookeeper返回的事件做出响应 就是对zookeeper中的节点进行监听
                    System.out.println(event.getType()+"---"+event.getPath());

                    //监听节点
                    try {
                        //首先到判断父节点是否存在，不存在则创建
                        if(!nodeExists(parentedNode)){
                            zk.create(parentedNode,"serversNode".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
                        }
                        zk.getChildren(parentedNode,true);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        if(!nodeExists(parentedNode)){
            zk.create(parentedNode,"serversNode".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
        }
    }

    /*
    实现服务器注册：
     */
    public void registerServer(String hostName) throws KeeperException, InterruptedException {

        //首先到判断父节点是否存在，不存在则创建
        if(!nodeExists(parentedNode)){
            zk.create(parentedNode,"serversNode".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
            System.out.println("创建根节点");
        }

        //注册实质就是创建一个节点
        String path = zk.create(parentedNode + "/server", hostName.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);//CreateMode.EPHEMERAL_SEQUENTIAL 创建临时且带序号的节点，保证每台服务器名称不同
        System.out.println(hostName+" is starting "+path);
    }



    //业务模拟
    public void handleBussiness(String hostName) throws InterruptedException {
        System.out.println(hostName+" start working");
        Thread.sleep(Long.MAX_VALUE);
    }


    /*
    判断节点是否存在
     */

    public boolean nodeExists(String path){
        try {
            Stat stat = zk.exists(path, false);
            if(stat==null) {
                System.out.println("nodeExists false");
                return false;
            }else {
                System.out.println("nodeExists true");
                return true;
            }
        } catch (Exception e) {
            System.out.println("nodeExists异常："+e);
            return false;
        }
    }
    @Test
    public void test() throws KeeperException, InterruptedException {
        Stat stat = zk.exists(parentedNode, false);
        if(stat==null)
            System.out.println("true");
        else
            System.out.println("false");

    }


    public static void main(String args[]) throws IOException, KeeperException, InterruptedException {

    //获取zk连接
        DistrubuteServer server=new DistrubuteServer();
        server.getConnect();


    //用户zk连接注册服务器信息
        server.registerServer("192.168.0.213");

    //处理业务
        //创建子线程实现业务,这里用一个方法模拟
        server.handleBussiness("192.168.0.213");




    }
}
