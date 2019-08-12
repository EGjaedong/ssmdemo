package cn.webdemo.ssm.service;

import cn.webdemo.ssm.domain.Permission;
import cn.webdemo.ssm.domain.Role;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 63042 on 2019/7/17.
 *
 */
public interface RoleService {
    List<Role> findAll() throws Exception;

    void save(Role role) throws Exception;

    Role findById(int roleId) throws Exception;

    List<Permission> findOtherPermission(int roleId) throws Exception;

    void addPermissionToRole(int roleId, Integer[] permissionIds);
}
