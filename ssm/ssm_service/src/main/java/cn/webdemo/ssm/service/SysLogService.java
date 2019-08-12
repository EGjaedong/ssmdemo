package cn.webdemo.ssm.service;

import cn.webdemo.ssm.domain.Syslog;

import java.util.List;

/**
 * Created by 63042 on 2019/7/18.
 *
 */
public interface SysLogService {

    void save(Syslog log);

    List<Syslog> findAll() throws Exception;
}
