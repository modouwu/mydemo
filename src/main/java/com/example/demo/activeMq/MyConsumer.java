package com.example.demo.activeMq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class MyConsumer {

    public static void main(String[] args) throws JMSException {
        //连接broker，就是我们刚刚防火墙里面打开的那个端口
        ConnectionFactory cf=  new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
        String condition="asdf>8";
        //创建了一个队列名称为 "user.queue"
        //Destination  destination=new ActiveMQQueue("user.queuer");
        Connection conn=cf.createConnection();
        Session session=conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination queue = session.createQueue("user.queuer");
        MessageConsumer consumer = session.createConsumer(queue,condition);
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                if(message instanceof Message){
                    System.out.println("successful"+message.toString());
                    try {
                        message.acknowledge();
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        conn.start();

        /*try {
            //自动确认消息的接受
            //Destination  destination=session.createQueue("user.queuer");
            //创建一个消费者
            //MessageConsumer consumer=session.createConsumer(destination,condition);
            //MessageConsumer consumer=session.createConsumer(destination);

            while(true) {
                consumer.setMessageListener(new MessageListener() {
                    @Override
                    public void onMessage(Message message) {
                        if(message instanceof Message){
                            System.out.println("successful"+message.toString());4                try {
                                message.acknowledge();
                            } catch (JMSException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
            }
            consumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    if(message instanceof Message){
                        System.out.println("successful"+message.toString());
                        try {
                            message.acknowledge();
                        } catch (JMSException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            conn.start();
            //同步阻塞等待接受消息
            MapMessage message=(MapMessage)consumer.receive();
            System.out.println("qwertyuiop"+message.getInt("age") +" "+ message.getString("userId")+" "+message.getString("userName"));

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
        }*/

    }
}
