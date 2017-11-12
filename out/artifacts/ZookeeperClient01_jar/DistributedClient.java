package com.lin.bigdata.zkdisc;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lin on 2017/11/5.
 */
public class DistributedClient {

    private static final String connectionString="192.168.0.210:2181,192.168.0.211:2181,192.168.0.212:2181";
    private static final int sessionTimeout=2000;
    private static final String parentedNode="/servers";

    private volatile List<String> serverList=null;
    private ZooKeeper zk=null;


    public void getConnect() throws IOException, KeeperException, InterruptedException { //建立连接并监听

        zk=new ZooKeeper(connectionString, sessionTimeout,new Watcher(){
            @Override
            public void process(WatchedEvent event) {

                try {
                    //重新获取服务器列表，并注册监听
                    getServerList();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }


    //获取服务器子节点信息，并对父节点进行监听
    public void getServerList() throws KeeperException, InterruptedException {

        List<String> children = zk.getChildren(parentedNode, true);
        List<String> servers=new ArrayList<>();

        for (String child:children){
            //child只是子节点的节点名，需要访问还要加上父节点名字才
            byte[] data = zk.getData(parentedNode + "/" + child, false, null);
            servers.add(new String(data));
        }
        serverList=servers;


    }

    public void handleBusiness() throws InterruptedException {

        System.out.println("server");
        for(String server:serverList){
            System.out.println("serverNode:"+server);
        }
        Thread.sleep(Long.MAX_VALUE);
    }



    public static void main(String args[]) throws InterruptedException, IOException, KeeperException {

        //创建zk连接
        DistributedClient  client=new DistributedClient();
        client.getConnect();
        //获取节点信息，并实现监听
        client.getServerList();
        //业务线程启动
        client.handleBusiness();
    }
}
