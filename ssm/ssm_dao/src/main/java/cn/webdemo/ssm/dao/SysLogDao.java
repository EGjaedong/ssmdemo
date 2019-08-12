package cn.webdemo.ssm.dao;

import cn.webdemo.ssm.domain.Syslog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 63042 on 2019/7/18.
 *
 */
//@Repository("sysLogDao")
public interface SysLogDao {

    @Insert("insert into syslog(visitTime,username,ip,url,executionTime,method) " +
            " values(#{visitTime}, #{username}, #{ip}, #{url}, #{executionTime}, #{method})")
    void save(Syslog log);

    @Select("select * from syslog")
    List<Syslog> findAll() throws Exception;
}
