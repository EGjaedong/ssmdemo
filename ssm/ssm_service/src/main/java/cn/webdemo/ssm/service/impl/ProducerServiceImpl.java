package cn.webdemo.ssm.service.impl;

import cn.webdemo.ssm.domain.Syslog;
import cn.webdemo.ssm.service.ProducerService;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.annotation.Resource;
import javax.jms.*;
import javax.print.attribute.standard.Destination;

public class ProducerServiceImpl implements ProducerService {
    @Autowired
    private JmsTemplate jmsTemplate;

    /*@Resource(name = "queueDestination")
    private ActiveMQQueue destination;*/

    @Override
    public void sendMessage(final Syslog syslog) {
        jmsTemplate.convertAndSend(syslog);
        System.out.println("消息已发送");
    }
}
