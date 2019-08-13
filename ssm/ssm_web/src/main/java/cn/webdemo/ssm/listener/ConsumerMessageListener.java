package cn.webdemo.ssm.listener;

import cn.webdemo.ssm.domain.Syslog;
import cn.webdemo.ssm.service.SysLogService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jms.*;
import java.io.IOException;
import java.util.Date;

public class ConsumerMessageListener implements MessageListener {
    @Autowired
    private SysLogService logService;

    @Override
    public void onMessage(Message message) {
        System.out.println("[receive message]");
        TextMessage textMessage = (TextMessage) message;
        try {
            String json = textMessage.getText();

            ObjectMapper mapper = new ObjectMapper();

            Syslog syslog = mapper.readValue(json, Syslog.class);
            System.out.println(syslog);
            logService.save(syslog);
        }catch (JMSException e){
            System.out.println("日志存储失败");
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
