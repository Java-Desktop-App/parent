package io.github.jast90.zk.client;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.concurrent.TimeUnit;

public class SessionManager {

    private final static String current_config = "config";
    private final static String CURRENT_PATH = "path";

    private static Cache<String, Object> cache = Caffeine.newBuilder()
            .expireAfterWrite(10, TimeUnit.MINUTES)
            .maximumSize(10_000)
            .build();

    public static ZookeeperConfig getCurrentConfig(){
        ZookeeperConfig zookeeperConfig = (ZookeeperConfig) cache.getIfPresent(current_config);
        return zookeeperConfig;
    }

    public static void setCurrentConfig(ZookeeperConfig zookeeperConfig){
        cache.put(current_config,zookeeperConfig);
    }

    public static String getCurrentPath(){
        String path = (String) cache.getIfPresent(CURRENT_PATH);
        System.out.println("current path:"+path);
        return path;
    }

    public static void setCurrentPath(String path){
        cache.put(CURRENT_PATH,path);
    }

}
