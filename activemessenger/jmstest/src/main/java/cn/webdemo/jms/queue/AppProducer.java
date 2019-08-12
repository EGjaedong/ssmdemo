package cn.webdemo.jms.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 队列模式生产者
 */
public class AppProducer {
    private static final String url = "tcp://192.168.0.104:61616";
    private static final String queueName = "queue-test";

    public static void main(String[] args) {
        // 1、创建ConnectionFactory
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(url);
        Connection connection = null;
        try {
            // 2、创建Connection
            connection = factory.createConnection();
            // 3、启动连接
            connection.start();
            // 4、创建会话
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            // 5、创建一个目标
            Queue queue = session.createQueue(queueName);
            // 6、创建一个生产者
            MessageProducer producer = session.createProducer(queue);
            for (int i = 0; i < 100; i++) {
                // 7、创建消息
                TextMessage textMessage = session.createTextMessage("test " + i);
                // 8、发布消息
                producer.send(textMessage);
                System.out.println("发送消息 " + textMessage.getText());
            }
        } catch (JMSException e) {
            e.printStackTrace();
            System.out.println("出错了");
        }
        finally {
            // 9、关闭资源
            if (connection != null){
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
