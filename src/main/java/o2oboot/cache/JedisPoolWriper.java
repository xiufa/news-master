package o2oboot.cache;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolWriper {

    private JedisPool jedisPool;

    public JedisPoolWriper(JedisPoolConfig jedisPoolConfig, final String host, final int port){

        jedisPool=new JedisPool(jedisPoolConfig,host,port);

    }

    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    public JedisPool getJedisPool() {
        return jedisPool;
    }
}
