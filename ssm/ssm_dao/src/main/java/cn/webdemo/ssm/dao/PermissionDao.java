package cn.webdemo.ssm.dao;

import cn.webdemo.ssm.domain.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 63042 on 2019/7/17.
 *
 */
//@Repository("permissionDao")
public interface PermissionDao {

    @Select("select * from permission where id in (select permissionId from role_permission where roleId = #{roleId})")
    public List<Permission> findPermissionByRoleId(int roleId);

    @Select("select * from permission")
    public List<Permission> findAll() throws Exception ;

    @Insert("insert into permission(permissionName, url) values(#{permissionName}, #{url})")
    void save(Permission permission) throws Exception ;
}
