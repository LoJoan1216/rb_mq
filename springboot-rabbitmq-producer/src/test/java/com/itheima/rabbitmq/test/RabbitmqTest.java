package com.itheima.rabbitmq.test;

import com.itheima.rabbitmq.config.ProducerConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Joan
 * @date 2019-10-31 19:07
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitmqTest {
    @Autowired
    private RabbitTemplate rabbitTemplate;


    @Test
    public void test() {
        rabbitTemplate.convertAndSend(ProducerConfig.ITEM_TOPIC_EXCHANGE,"item_insert","添加商品,key 为 insert");
        rabbitTemplate.convertAndSend(ProducerConfig.ITEM_TOPIC_EXCHANGE,"item_delete","删除商品,key 为 delete");
        rabbitTemplate.convertAndSend(ProducerConfig.ITEM_TOPIC_EXCHANGE,"item_update","修改商品,key 为 update");
        rabbitTemplate.convertAndSend(ProducerConfig.ITEM_TOPIC_EXCHANGE,"item_select","查询商品,key 为 select");

    }


}
