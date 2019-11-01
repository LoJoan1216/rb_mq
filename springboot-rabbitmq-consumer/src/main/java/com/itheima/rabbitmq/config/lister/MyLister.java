package com.itheima.rabbitmq.config.lister;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author Joan
 * @date 2019-10-31 19:04
 */
@Component
public class MyLister {
    @RabbitListener(queues = "item_queue")
    public void MyLister1(String message) {
        System.out.println("消费者接收到的数据是：" + message);
    }
}
