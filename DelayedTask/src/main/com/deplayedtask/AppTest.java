package com.deplayedtask;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Tuple;

import java.util.Calendar;
import java.util.Set;

/**
 * @author apktool
 * @date 2018-06-27 14:11
 */
public class AppTest {
    private static final String ADDR = "127.0.0.1";
    private static final int PORT = 6379;
    private static final String PASSWORD = "redis";
    private JedisPool jedisPool = new JedisPool(ADDR, PORT);
    private Jedis jedis;

    public AppTest() {
        jedis = jedisPool.getResource();
        jedis.auth(PASSWORD);
    }

    private Jedis getJedis() {
        return jedis;
    }

    //生产者,生成5个订单放进去
    public void productionDelayMessage() {
        for (int i = 0; i < 5; i++) {
            Calendar cal1 = Calendar.getInstance();
            cal1.add(Calendar.SECOND, 10);

            int second3Later = (int) (cal1.getTimeInMillis() / 1000);
            getJedis().zadd("OrderId", second3Later, "OID0000001" + i);

            System.out.println(System.currentTimeMillis() + "ms:redis生成了一个订单任务：订单ID为" + "OID0000001" + i);
        }
    }

    public void consumerDelayMessage() {
        Jedis jedis = this.getJedis();
        while (true) {
            Set<Tuple> items = jedis.zrangeWithScores("OrderId", 0, 1);
            if (items == null || items.isEmpty()) {
                System.out.println("当前没有等待的任务");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                continue;
            }

            int score = (int) ((Tuple) items.toArray()[0]).getScore();
            Calendar cal = Calendar.getInstance();

            int nowSecond = (int) (cal.getTimeInMillis() / 1000);

            if (nowSecond >= score) {
                String orderId = ((Tuple) items.toArray()[0]).getElement();
                Long num = jedis.zrem("OrderId", orderId);
                System.out.println(System.currentTimeMillis() + "ms:redis消费了一个任务：消费的订单OrderId为" + orderId);
            }
        }
    }

    public static void main(String[] args) {
        AppTest appTest = new AppTest();
        appTest.productionDelayMessage();
        appTest.consumerDelayMessage();
    }
}
