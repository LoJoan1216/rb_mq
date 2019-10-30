package com.itheima.rabbitmq.simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author Joan
 * @date 2019-10-30 14:36
 */

/**
 * 消息提供者类
 */
public class Producer {

    static final String QUEUE_NAME = "simple_queue";

    public static void main(String[] args) throws Exception{

        ConnectionFactory connectionFactory = new ConnectionFactory();
        //设置默认地址
       /* connectionFactory.setHost("localhost");
        //设置默认端口号
        connectionFactory.setPort(5672);
        //设置主机名称
        connectionFactory.setVirtualHost("/itcast");
        //连接用户名
        connectionFactory.setUsername("guest");
        //设置密码
        connectionFactory.setPassword("guest");*/

        //创建连接
        Connection connection = connectionFactory.newConnection();

        //创建频道
        Channel channel = connection.createChannel();

        /**
         * 1.队列名称
         * 2.是否定义持久队列
         * 3.是否独占本次连接
         * 4.是否在不适用的时候自动删除队列
         * 5.队列其他参数
         */
        channel.queueDeclare(QUEUE_NAME,true,false,false,null);

        //要发送的信息
        String message ="hello rabbitmq!";

        /**
         * 1.交换机名称，没有的话默认为default exchange
         * 2.路由key
         * 3.消息其他属性
         * 4.消息内容
         */
        channel.basicPublish("",QUEUE_NAME,null,message.getBytes());
        channel.close();
        connection.close();

    }
}
