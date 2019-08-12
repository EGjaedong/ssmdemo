package cn.webdemo.ssm.service.impl;

import cn.webdemo.ssm.dao.RoleDao;
import cn.webdemo.ssm.domain.Permission;
import cn.webdemo.ssm.domain.Role;
import cn.webdemo.ssm.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 63042 on 2019/7/17.
 *
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;

    @Override
    public List<Role> findAll() throws Exception {
        return roleDao.findAll();
    }

    @Override
    public void save(Role role) throws Exception {
        roleDao.save(role);
    }

    @Override
    public Role findById(int roleId) throws Exception {
        return roleDao.findById(roleId);
    }

    @Override
    public List<Permission> findOtherPermission(int roleId) throws Exception {
        return roleDao.findOtherPermission(roleId);
    }

    @Override
    public void addPermissionToRole(int roleId, Integer[] permissionIds) {
        for (int pid : permissionIds) {
            roleDao.addPermissionToRole(roleId, pid);
        }
    }
}
