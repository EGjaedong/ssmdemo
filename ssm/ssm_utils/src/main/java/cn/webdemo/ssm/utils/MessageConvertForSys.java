package cn.webdemo.ssm.utils;

import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Session;

public class MessageConvertForSys implements MessageConverter {
    @Override
    public javax.jms.Message toMessage(Object object, Session session) throws JMSException, MessageConversionException {
        System.out.println("[toMessage]");
        ObjectMessage objectMessage = session.createObjectMessage();
        objectMessage.setJMSExpiration(1000);
        objectMessage.setObjectProperty("key1", object+"_add");
        return objectMessage;
    }

    @Override
    public Object fromMessage(javax.jms.Message message) throws JMSException, MessageConversionException {
        System.out.println("[fromMessage]");
        ObjectMessage objectMessage = (ObjectMessage) message;
        return objectMessage.getObjectProperty("key1");
    }
}
