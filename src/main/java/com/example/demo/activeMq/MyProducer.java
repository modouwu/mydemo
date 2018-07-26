package com.example.demo.activeMq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;

import javax.jms.*;

public class MyProducer {
    public static void main(String[] args) {
        //连接broker，就是我们刚刚防火墙里面打开的那个端口
        ConnectionFactory cf=  new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
        //创建了一个队列名称为 "user.queue"
        Destination destination=new ActiveMQQueue("user.queuer");
        Connection conn=null;

        try {
            //从连接工程里面获取一个新连接
            conn=cf.createConnection();
            //自动确认消息的发送
            Session session=conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
            //创建一个消息生产者，并且配置发送消息的地址
            MessageProducer producer=session.createProducer(destination);
            //创建一个map类型的消息
            MapMessage message=session.createMapMessage();
            message.setString("userId", "123456");
            message.setString("userName", "李四");
            message.setInt("age", 18);
            message.setIntProperty("asdf",12);
            //发送
            producer.send(message);
            //关闭
            System.out.println("produce success");
            session.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally{
            if(conn!=null){
                try {
                    conn.close();
                } catch (Exception e2) {
                    // TODO: handle exception
                }
            }
        }

    }
}
