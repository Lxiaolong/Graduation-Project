package cn.net.sunet.sunetcloud.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Author:  Leland
 * Email:   AC_Dreamer@163.com
 * Website: www.sunet.net.cn
 * Date:    2017/9/18
 * Function: redis本地信息存储工具类
 */
public class Jedisutils {
    private static final String REDIS_HOST = "127.0.0.1";
    private static final int REDIS_PORT = 6379;

    private JedisPool jedisPool;

    private Jedisutils() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(8000);
        // 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。
        config.setMaxIdle(10);
        jedisPool = new JedisPool(config, REDIS_HOST, REDIS_PORT);
    }
    private static class JedisInstance {
        private static final Jedisutils INSTANCE = new Jedisutils();
    }
    public static Jedisutils getInstance() {
        return JedisInstance.INSTANCE;
    }
    public Jedis getJedis() {
        Jedis jedis = jedisPool.getResource();
        //jedis.auth("UIVf18MlOwjt");
        return jedis;
    }
    public void closeJedis(Jedis jedis) {
        if (jedis != null)
            jedis.close();
    }
    public void destroy() {
        jedisPool.close();
    }
}
