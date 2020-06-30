package o2oboot.cache;

import redis.clients.jedis.Jedis;
import redis.clients.util.SafeEncoder;

import java.util.Set;

public class JedisUtil {
    private JedisPoolWriper jedisPoolWriper;
    private Keys keys;
    private Strings strings;


    public JedisUtil(JedisPoolWriper jedisPoolWriper){
        this.jedisPoolWriper=jedisPoolWriper;
    }

    public Jedis getJedis(){
        return jedisPoolWriper.getJedisPool().getResource();
    }

    public class Keys{
        public String flushAll(){
            Jedis jedis=getJedis();
            String res=jedis.flushAll();

            jedis.close();
            return res;
        }

        public long del(String... keys){
            Jedis jedis=getJedis();
            long count=jedis.del(keys);
            jedis.close();
            return count;
        }

        public boolean exists(String key){
            Jedis jedis=getJedis();
            boolean res=jedis.exists(key);

            jedis.close();
            return res;
        }

        public Set<String> keys(String key){
            Jedis jedis=getJedis();
            Set<String> res=jedis.keys(key);
            jedis.close();
            return res;
        }
    }

    public class Strings{

        public String get(String key){
            Jedis jedis=getJedis();
            String res=jedis.get(key);
            jedis.close();
            return res;
        }

        public String set(String key,String value){
            return set(SafeEncoder.encode(key), SafeEncoder.encode(value));

        }
        public String set(byte[] key,byte[] value){
            Jedis jedis=getJedis();
            String res=jedis.set(key,value);
            jedis.close();
            return res;
        }


    }

}
