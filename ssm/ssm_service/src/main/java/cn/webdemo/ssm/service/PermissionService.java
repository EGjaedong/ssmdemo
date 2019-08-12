package cn.webdemo.ssm.service;

import cn.webdemo.ssm.domain.Permission;

import java.util.List;

/**
 * Created by 63042 on 2019/7/17.
 *
 */
public interface PermissionService {

    List<Permission> findAll() throws Exception;

    void save(Permission permission) throws Exception;
}
