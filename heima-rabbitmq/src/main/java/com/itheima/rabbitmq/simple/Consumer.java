package com.itheima.rabbitmq.simple;

/**
 * @author Joan
 * @date 2019-10-30 14:54
 */

import com.itheima.rabbitmq.util.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * 消费者类
 */
public class Consumer {

    public static void main(String[] args) throws Exception {

        Connection connection = ConnectionUtil.getConnection();
        //创建频道
        final Channel channel = connection.createChannel();

        /**
         * 1.队列名称
         * 2.是否定义持久化队列
         * 3.是否独占本次连接
         * 4.是否在不使用时候删除队列
         * 5.队列其他属性
         *
         */
        channel.queueDeclare(Producer.QUEUE_NAME, true, false, false, null);
        //创建消费者
       DefaultConsumer consumer = new DefaultConsumer(channel){

           /**
            *
            * @param consumerTag 消息者标签 ，在channel.basicConsumer中可以指定
            * @param envelope 消息包的内容，可以从中获取数据
            * @param properties 属性信息
            * @param body 消息
            * @throws IOException
            */
           @Override
           public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {


               System.out.println("路由key："+envelope.getRoutingKey());
               System.out.println("交换机："+envelope.getExchange());
               System.out.println("消息id："+envelope.getDeliveryTag());
               System.out.println("接受到的消息为："+new String(body,"utf-8"));


           }
       };

        /**
         * 1.队列名称
         * 2.是否自动确认，设置为true为表示消息接收到自动向mq回复接收到了，mq接收到回复会删除消
         * 息，设置为false则需要手动确认
         * 3.消息接受后回调
         */
        channel.basicConsume(Producer.QUEUE_NAME,true,consumer);


    }

}
