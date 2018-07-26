package com.example.demo.activeMq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQMessage;

import javax.jms.*;

//持久化订阅
public class MyConsumer2 {

    public static void main(String[] args) throws JMSException {
        String brokerUrl = "tcp://127.0.0.1:61616";
        String queueName = "user.queuer";
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(brokerUrl);
        ActiveMQConnection connection = (ActiveMQConnection)factory.createConnection();
        final Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination queue = session.createQueue(queueName);
        MessageConsumer consumer = session.createConsumer(queue);
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                try{
                    ActiveMQMessage m = (ActiveMQMessage)message;
                    System.out.println("successful"+m.toString());
                }catch(Exception e){
                    //
                    e.printStackTrace();
                }
            }
        });
        connection.start();

    }
}
