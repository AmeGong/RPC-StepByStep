import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.Test;

import java.io.IOException;

public class ZookeeperDemo {

    @Test
    public void connectZkCluster() throws IOException, KeeperException, InterruptedException {

        // 构造方法
        // ZooKeeper(String connectString, int sessionTimeout, Watcher watcher)

        // 匿名对象形式
        ZooKeeper zooKeeper = new ZooKeeper(
                "127.0.0.1:2181",
                20000,
                new Watcher() {
                    @Override
                    public void process(WatchedEvent watchedEvent) {
                        // 发生变更的节点路径
                        String path = watchedEvent.getPath();
                        System.out.println("path:" + path);

                        // 通知状态
                        Watcher.Event.KeeperState state = watchedEvent.getState();
                        System.out.println("KeeperState:" + state);

                        // 事件类型
                        Watcher.Event.EventType type = watchedEvent.getType();
                        System.out.println("EventType:" + type);
                    }
                }
        );

        // 关闭连接
        zooKeeper.close();

        // Lamdba形式
        ZooKeeper zk = new ZooKeeper(
                "192.168.1.3:2181,192.168.1.4:2181,192.168.1.5:2181",
                20000,
                watchedEvent -> {
                    // 发生变更的节点路径
                    String path = watchedEvent.getPath();
                    System.out.println("path:" + path);

                    // 通知状态
                    Watcher.Event.KeeperState state = watchedEvent.getState();
                    System.out.println("KeeperState:" + state);

                    // 事件类型
                    Watcher.Event.EventType type = watchedEvent.getType();
                    System.out.println("EventType:" + type);
                }
        );

        zk.close();
    }
    @Test
    public void manageData() throws KeeperException, InterruptedException, IOException {

        // 创建zk连接
        ZooKeeper zk = new ZooKeeper(
                "127.0.0.1:2181",
                20000,
                null
        );

        // 创建节点
//        zk.create("/abc", "123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        // 获取节点数据
        // getData(String path, boolean watch, Stat stat);
        Stat stat = new Stat();
        byte[] data = zk.getData("/abc", false, stat);
        System.out.println(new String(data));       // 数据内容 123
        System.out.println(stat.getDataLength());   // 节点状态（数据长度） 3

        // 对/abc进行watch
        zk.getData("/abc",
                watchedEvent -> {
                    System.out.println("path:" + watchedEvent.getPath());
                    System.out.println("KeeperState:" + watchedEvent.getState());
                    System.out.println("EventType:" + watchedEvent.getType());
                },
                null);

        // 设置节点数据
        // setData(String path, byte[] data, int version)
        // 指定version为-1，表示不关心版本
        zk.setData("/abc", "456".getBytes(), -1);

        // 设置两次，第二次不会触发通知
        zk.setData("/abc", "789".getBytes(), -1);

        // 阻塞，以等待通知
        Thread.sleep(1000);
        zk.close();
    }
}
