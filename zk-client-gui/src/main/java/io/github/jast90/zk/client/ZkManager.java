package io.github.jast90.zk.client;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.imps.CuratorFrameworkState;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class ZkManager {

    public static CuratorFramework getConnection() {
        ZookeeperConfig currentConfig = SessionManager.getCurrentConfig();
        if(currentConfig != null){
            return getConnection(currentConfig);
        }
        return null;
    }

    public static CuratorFramework getConnection(ZookeeperConfig zookeeperConfig) {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient(String.format("%s:%s",zookeeperConfig.getHost(),
                zookeeperConfig.getPort()), retryPolicy);
        client.start();
        return client;
    }
}
