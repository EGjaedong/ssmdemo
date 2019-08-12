package cn.webdemo.ssm.service;

import cn.webdemo.ssm.domain.Syslog;

public interface ProducerService {
    void sendMessage(Syslog syslog);
}
