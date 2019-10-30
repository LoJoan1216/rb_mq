package com.itheima.rabbitmq.rount;

import com.itheima.rabbitmq.util.ConnectionUtil;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * @author Joan
 * @date 2019-10-30 16:41
 */
public class Consumer1 {
    public static void main(String[] args) throws Exception{
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(Producer.DIRECT_EXCHANGE, BuiltinExchangeType.DIRECT);
        channel.queueDeclare(Producer.DIRECT_QUEUE_INSERT,true,false,false,null);
        channel.queueBind(Producer.DIRECT_QUEUE_INSERT,Producer.DIRECT_EXCHANGE,"insert");

    }
}
