package com.skrein.zk;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;

/**
 * @Author: hujiansong
 * @Date: 2019/5/16 16:21
 * @since: 1.8
 */
@Slf4j
public class ZKTest implements Watcher {

    private ZooKeeper zk = null;

    private CountDownLatch connectedSemaphore = new CountDownLatch(1);

    public void connectZK(String url) {
        try {
            // ZK客户端允许我们将ZK服务器的所有地址都配置在这里
            zk = new ZooKeeper(url, 6000, this);
            // 使用CountDownLatch.await()的线程（当前线程）阻塞直到所有其它拥有CountDownLatch的线程执行完毕（countDown()结果为0）
            connectedSemaphore.await();
            log.info("连接创建成功!");
        } catch (InterruptedException e) {
            log.error("连接创建失败，发生 InterruptedException , e " + e.getMessage(), e);
        } catch (IOException e) {
            log.error("连接创建失败，发生 IOException , e " + e.getMessage(), e);
        }
    }

    public void createNode(String path) throws IOException {
        try {
            ByteArrayOutputStream byteArrayOS = new ByteArrayOutputStream();
            ObjectOutputStream stream = new ObjectOutputStream(byteArrayOS);
            stream.writeObject("lock");
            stream.close();
            zk.create(path, byteArrayOS.toByteArray(),ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        } catch (KeeperException e) {
            log.error("连接创建失败，发生 KeeperException , e " + e.getMessage(), e);
        } catch (InterruptedException e) {
            log.error("连接创建失败，发生 InterruptedException , e " + e.getMessage(), e);
        }
    }

    public void existsNode(String path){
        try {
            zk.exists(path,this);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ZKTest zkTest = new ZKTest();
        String url = "localhost:2181";
        zkTest.connectZK(url);
        zkTest.createNode("/skrein/");
//        zkTest.existsNode("/skrein/node1");
        System.in.read();
    }

    public void process(WatchedEvent event) {
        log.info("收到Zk通知{}", event.getState().toString());
        log.info("收到Zk通知{}", event.getType().toString());
        log.info("收到Zk通知{}", event.getPath());
        log.info("收到Zk通知{}", event.getWrapper().toString());
        if (event.getState() == Event.KeeperState.SyncConnected) {
            this.connectedSemaphore.countDown();
        }

        if (event.getType() == Event.EventType.NodeCreated) {
            log.info("创建节点{}", event.getPath());
        }

    }
}
