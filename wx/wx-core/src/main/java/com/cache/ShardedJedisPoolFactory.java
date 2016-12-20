package com.cache;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.util.StringUtils;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedisPool;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2016/11/25.
 */
public class ShardedJedisPoolFactory implements FactoryBean {
    public String getHostAndPorts() {
        return hostAndPorts;
    }

    public void setHostAndPorts(String hostAndPorts) {
        this.hostAndPorts = hostAndPorts;
    }

    String hostAndPorts;

    @Override
    public Object getObject() throws Exception {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(100);
        poolConfig.setMinIdle(10);
        poolConfig.setMaxWaitMillis(3000);
        poolConfig.setMaxTotal(50);

        List<JedisShardInfo> list = new ArrayList<>();
        int port;
        String host;
        JedisShardInfo info;
        if (!StringUtils.isEmpty(hostAndPorts)) {
            String[] tmp = hostAndPorts.split(",");
            for (String item : tmp) {
                if (StringUtils.isEmpty(item)) continue;
                String[] hp = item.split(":");
                if (hp.length == 2) {
                    host = hp[0];
                    port = Integer.parseInt(hp[1]);
                    info = new JedisShardInfo(host, port);
                } else {
                    info = new JedisShardInfo(hp[0], 6379);
                }

                list.add(info);
            }
        }
        ShardedJedisPool pool = new ShardedJedisPool(poolConfig, list);
        return pool;
    }

    @Override
    public Class<?> getObjectType() {
        return ShardedJedisPool.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
