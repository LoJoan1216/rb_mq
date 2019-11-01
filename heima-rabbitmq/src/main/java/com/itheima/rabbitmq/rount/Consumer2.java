package com.itheima.rabbitmq.rount;

import com.itheima.rabbitmq.util.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @author Joan
 * @date 2019-10-30 16:41
 */
public class Consumer2 {
    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(Producer.DIRECT_EXCHANGE, BuiltinExchangeType.DIRECT);
        channel.queueDeclare(Producer.DIRECT_QUEUE_UPDATE, true, false, false, null);
        channel.queueBind(Producer.DIRECT_QUEUE_UPDATE, Producer.DIRECT_EXCHANGE, "update");
        DefaultConsumer consumer = new DefaultConsumer(channel) {
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
                System.out.println("路由key：" + envelope.getRoutingKey());
                System.out.println("交换机：" + envelope.getExchange());
                System.out.println("消息id：" + envelope.getDeliveryTag());
                System.out.println("消费者2-接受到的消息为：" + new String(body, "utf-8"));
            }
        };
        channel.basicConsume(Producer.DIRECT_QUEUE_UPDATE, true, consumer);

    }
}
