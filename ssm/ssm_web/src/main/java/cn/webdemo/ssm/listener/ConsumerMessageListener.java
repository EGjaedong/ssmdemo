package cn.webdemo.ssm.listener;

import cn.webdemo.ssm.domain.Syslog;
import cn.webdemo.ssm.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import java.util.Date;

public class ConsumerMessageListener implements MessageListener {
    @Autowired
    private SysLogService logService;

    @Override
    public void onMessage(Message message) {
        System.out.println("[receive message]");
        ObjectMessage objectMessage = (ObjectMessage) message;
        try {
            String key1 = objectMessage.getStringProperty("key1");
            System.out.println(key1);

            Syslog syslog = (Syslog) objectMessage.getObjectProperty("key1");
            System.out.println(syslog);
        }catch (JMSException e){
            System.out.println("日志存储失败");
        }

    }
}
