package com.itheima.rabbitmq.work;

import com.itheima.rabbitmq.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * @author Joan
 * @date 2019-10-30 15:13
 */
public class Procucer {
    static final String QUEUE_NAME="work_queue";
    public static void main(String[] args) throws Exception{
        //创建连接
        Connection connection = ConnectionUtil.getConnection();
        //创建频道
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,true,false,false,null);
        for (int i = 0; i < 30; i++) {
            String message="hello  rabbitmq!!";
            channel.basicPublish("",QUEUE_NAME,null,message.getBytes());
            System.out.println("已发送消息："+message);
        }
        channel.close();
        connection.close();
    }
}
