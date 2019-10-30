package com.itheima.rabbitmq.ps;

import com.itheima.rabbitmq.util.ConnectionUtil;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * @author Joan
 * @date 2019-10-30 15:48
 */
public class Producer {

    static final String FANOUT_EXCHANGE = "fanout_exchange";
    static final String FANOUT_QUEUE1 = "fanout_queue1";
    static final String FANOUT_QUEUE2 = "fanout_queue2";

    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        /**
         * 1.交换机名称
         * 2.交换机类型
         */
        channel.exchangeDeclare(FANOUT_EXCHANGE, BuiltinExchangeType.FANOUT);
        channel.queueDeclare(FANOUT_QUEUE1, true, false, false, null);
        channel.queueDeclare(FANOUT_QUEUE2, true, false, false, null);
        //绑定交换机
        channel.queueBind(FANOUT_QUEUE1, FANOUT_EXCHANGE, "");
        channel.queueBind(FANOUT_QUEUE2, FANOUT_EXCHANGE, "");
        for (int i = 0; i < 30; i++) {
            String message = "hello fanout!!";
            channel.basicPublish(FANOUT_EXCHANGE, "", null, message.getBytes());

            System.out.println("消息："+message);
        }
        channel.close();
        connection.close();
    }
}
