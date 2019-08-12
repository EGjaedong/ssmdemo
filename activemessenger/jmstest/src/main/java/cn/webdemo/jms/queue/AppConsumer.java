package cn.webdemo.jms.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 队列模式消费者，ActiveMQ保证所有连接基本平均分配消息。
 */
public class AppConsumer {
    private static final String url = "tcp://192.168.0.104:61616";
    private static final String queueName = "queue-test";

    public static void main(String[] args) {
        // 1、创建工厂
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(url);
        // 2、创建连接
        Connection connection = null;
        try {
            connection = factory.createConnection();
            // 3、开启连接
            connection.start();
            // 4、创建会话
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            // 5、创建目标
            Destination destination = session.createQueue(queueName);
            // 6、创建消费者
            MessageConsumer consumer = session.createConsumer(destination);
            // 7、创建一个监听器，并处理消息
            consumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    TextMessage textMessage = (TextMessage) message;
                    try {
                        System.out.println("接收消息 " + queueName + " - " + ((TextMessage) message).getText());
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (JMSException e) {
            e.printStackTrace();
        }

        // 8、关闭资源
        // 消费者持续消费，不应该关闭资源
        /*finally {
            if (connection != null){
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }*/
    }
}
