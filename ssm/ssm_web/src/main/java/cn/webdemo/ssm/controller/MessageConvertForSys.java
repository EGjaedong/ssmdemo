package cn.webdemo.ssm.controller;

import cn.webdemo.ssm.domain.Syslog;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;
import java.io.Serializable;

public class MessageConvertForSys implements MessageConverter {
    @Override
    public javax.jms.Message toMessage(Object object, Session session) throws JMSException, MessageConversionException {
        System.out.println("[toMessage]");
        TextMessage textMessage = session.createTextMessage();
        textMessage.setJMSExpiration(1000);
        Syslog syslog = (Syslog) object;
        ObjectMapper mapper = new ObjectMapper();
        String json = null;
        try {
            json = mapper.writeValueAsString(syslog);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        textMessage.setText(json);
        return textMessage;
    }

    @Override
    public Object fromMessage(javax.jms.Message message) throws JMSException, MessageConversionException {
        System.out.println("[fromMessage]");
        TextMessage textMessage = (TextMessage) message;
        return textMessage.getText();
    }
}
