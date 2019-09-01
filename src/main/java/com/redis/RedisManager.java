package com.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisManager {

    private static final int MAX_TOTAL = 1024;
    private static final int MAX_IDLE = 200;
    private static final int MAX_WAIT_MILLIS = 10000;
    private static final int TIME_OUT = 10000;
    private static final boolean TEST_ON_BORROW = true;
    private static String ADDR = "127.0.0.1";
    private static final int PORT = 6379;
    private static JedisPool jedisPool = null;
    static {
        try{
            var config = new JedisPoolConfig();
            config.setMaxTotal(MAX_TOTAL);
            config.setMaxIdle(MAX_IDLE);
            config.setMaxWaitMillis(MAX_WAIT_MILLIS);
            config.setTestOnBorrow(TEST_ON_BORROW);
            jedisPool = new JedisPool(config,ADDR,PORT,TIME_OUT);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public synchronized static Jedis getJedis(){
        try {
            if(jedisPool != null){
                Jedis jedis = jedisPool.getResource();
                return jedis;
            }else{
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void returnResource(final Jedis jedis){
        if(jedis!=null){
            jedisPool.returnResource(jedis);
        }
    }
}
