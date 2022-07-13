package com.epam.redis;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.io.IOException;

/**
 * @description: RedissonConfig
 * @author: taoz
 * @date: 2022/7/12 10:01
 */
@Configuration
@Profile("!unittest")
public class RedissonConfig {

    @Autowired
    private RedisProperties redisProperties;

    /**
     * redis单体配置
     *
     * @param
     * @return
     * @Author taoz
     * @Date 2022/7/12 11:31
     **/
    @Bean(destroyMethod = "shutdown")
    RedissonClient redissonClient() throws IOException {
        //1、创建配置
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://" + redisProperties.getHost() + ":" + redisProperties.getPort());//要早yml配置文件中配置好
        //  spring:redis
        return Redisson.create(config);
    }

    /**
     * cluster集群配置
     *
     * @param
     * @return
     * @Author taoz
     * @Date 2022/7/12 11:31
     **/
//    @Bean
//    public Redisson redisson() {
//        List<String> nodes = redisProperties.getCluster().getNodes();
//        //redisson版本是3.5，集群的ip前面要加上“redis://”，不然会报错，3.2版本可不加
//        List<String> clusterNodes = new ArrayList<>();
//        for (int i = 0; i < redisProperties.getCluster().getNodes().size(); i++) {
//            clusterNodes.add("redis://" + redisProperties.getCluster().getNodes().get(i));
//        }
//        Config config = new Config();
//        ClusterServersConfig clusterServersConfig = config.useClusterServers()
//                .addNodeAddress(clusterNodes.toArray(new String[clusterNodes.size()]));
//        clusterServersConfig.setPassword(redisProperties.getPassword());//设置密码
//        return (Redisson) Redisson.create(config);
//    }

}
