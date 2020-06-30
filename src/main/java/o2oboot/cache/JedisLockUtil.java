package o2oboot.cache;

import redis.clients.jedis.Jedis;

public class JedisLockUtil {

    private Jedis jedis;

    private int lockTime=15*1000;
    private int waitTime=15*1000;
    private String lockKey="lock";

    private boolean isLocked;

    public JedisLockUtil(JedisUtil jedisUtil) {
        this.jedis = jedisUtil.getJedis();

    }


    public boolean acquire(String test) throws InterruptedException {
        int timeout=waitTime;

        long newLockTime=System.currentTimeMillis()+lockTime+1;


        if(jedis.setnx(lockKey,String.valueOf(newLockTime))==1){
            isLocked=true;
            return true;
        }


        while(timeout>0){

            if(jedis.exists(test)){
                return false;
            }


            Long currentTime=Long.valueOf(jedis.get(lockKey));

            if(jedis.setnx(lockKey,String.valueOf(newLockTime))==1){

                System.out.println("未超时获取锁");
                isLocked=true;
                return true;
            }

            if(currentTime<System.currentTimeMillis()){
                long temp=System.currentTimeMillis();
                String oldTime=jedis.getSet(lockKey, String.valueOf(temp+lockTime));
                boolean a=Long.valueOf(oldTime)==(currentTime);
                boolean b=oldTime!=null;
                boolean c=oldTime.equals(String.valueOf(currentTime));
                if(oldTime!=null&&oldTime.equals(String.valueOf(currentTime))){
                    System.out.println("超时获取锁");
                    isLocked=true;
                    return true;
                }

            }


            timeout-=100;
            Thread.sleep(100);
        }
        return false;

    }


    public boolean release(){
        if(isLocked){
            String currentTime=jedis.get(lockKey);
            if(System.currentTimeMillis()<Long.valueOf(currentTime)){
                jedis.del(lockKey);
                System.out.println("释放锁");
                isLocked=false;
                return true;
            }
        }

        return false;
    }

}
