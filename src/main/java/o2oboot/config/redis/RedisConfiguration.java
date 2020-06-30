package o2oboot.config.redis;

import o2oboot.cache.JedisLockUtil;
import o2oboot.cache.JedisPoolWriper;
import o2oboot.cache.JedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisConfiguration {

    @Value("${redis.hostname}")
    private String hostname;
    @Value("${redis.port}")
    private int port;
    @Value("${redis.pool.maxActive}")
    private int maxTotal;
    @Value("${redis.pool.maxIdle}")
    private int maxIdle;
    @Value("${redis.pool.maxWait}")
    private long maxWaitMillis;
    @Value("${redis.pool.testOnBorrow}")
    private boolean testOnBorrow;


    @Autowired
    private JedisPoolConfig jedisPoolConfig;
    @Autowired
    private JedisPoolWriper jedisWritePool;
    @Autowired
    private JedisUtil jedisUtil;

    @Bean(name="jedisPoolConfig")
    public JedisPoolConfig getJedisPoolConfig(){
        JedisPoolConfig jedisPoolConfig=new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(maxTotal);
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        jedisPoolConfig.setTestOnBorrow(testOnBorrow);
        return jedisPoolConfig;
    }


    @Bean(name="jedisWritePool")
    public JedisPoolWriper getJedisPoolWriper(){
        JedisPoolWriper jedisPoolWriper=new JedisPoolWriper(jedisPoolConfig,hostname,port);
        return jedisPoolWriper;
    }

    @Bean(name = "jedisUtil")
    public JedisUtil createJedisUtil() {
        JedisUtil jedisUtil = new JedisUtil(jedisWritePool);
        return jedisUtil;
    }

    @Bean(name="jedisKeys")
    public JedisUtil.Keys getKeys(){
        JedisUtil.Keys keys=jedisUtil.new Keys();
        return keys;
    }
    @Bean(name="jedisStrings")
    public JedisUtil.Strings getStrings(){
        JedisUtil.Strings strings=jedisUtil.new Strings();
        return strings;
    }

    @Bean(name="jedisLockUtil")
    public JedisLockUtil getJedisLockUtil(){
        JedisLockUtil jedisLockUtil=new JedisLockUtil(jedisUtil);
        return jedisLockUtil;
    }


}
