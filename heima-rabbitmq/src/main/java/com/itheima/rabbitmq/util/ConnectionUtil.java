package com.itheima.rabbitmq.util;

/**
 * @author Joan
 * @date 2019-10-30 14:51
 */

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * 消费者工具类
 */
public class ConnectionUtil {
    public static Connection getConnection() throws Exception{
        //创建新工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();

        return connectionFactory.newConnection();

    }
}
