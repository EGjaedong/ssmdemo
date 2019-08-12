package cn.webdemo.ssm.service.impl;

import cn.webdemo.ssm.dao.SysLogDao;
import cn.webdemo.ssm.domain.Syslog;
import cn.webdemo.ssm.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 63042 on 2019/7/18.
 *
 */
@Service("sysLogService")
public class SysLogServiceImpl implements SysLogService {
    @Autowired
    private SysLogDao sysLogDao;

    @Override
    public void save(Syslog log) {
        sysLogDao.save(log);
    }

    @Override
    public List<Syslog> findAll() throws Exception {
        return sysLogDao.findAll();
    }
}
