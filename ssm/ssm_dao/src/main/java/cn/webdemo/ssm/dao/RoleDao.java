package cn.webdemo.ssm.dao;

import cn.webdemo.ssm.domain.Permission;
import cn.webdemo.ssm.domain.Role;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 63042 on 2019/6/30.
 *
 */
//@Repository("roleDao")
public interface RoleDao {
    /**
     * 根据用户id查询出所有对应的角色
     * @param userId
     * @return
     * @throws Exception
     */
    @Select("select * from role where id in (select roleId from users_role where userId = #{userId})")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roleName", column = "roleName"),
            @Result(property = "roleDesc", column = "roleDesc"),
            @Result(property = "permissions", column = "id", javaType = java.util.List.class, many = @Many(select = "cn.webdemo.ssm.dao.PermissionDao.findPermissionByRoleId"))
    })
    public List<Role> findRoleByUserId(int userId) throws Exception;

    @Select("select * from role")
    public List<Role> findAll() throws Exception ;

    @Insert("insert into role(roleName, roleDesc) values(#{roleName}, #{roleDesc}) ")
    void save(Role role) throws Exception ;

    @Select("select * from role where id = #{roleId}")
    Role findById(int roleId) throws Exception ;

    @Select("select * from permission where id not in(select permissionId from role_permission where roleId = #{roleId})")
    List<Permission> findOtherPermission(int roleId) throws Exception ;

    @Insert("insert into role_permission(permissionId, roleId) values(#{pid}, #{roleId})")
    void addPermissionToRole(@Param("roleId") int roleId, @Param("pid") int pid);
}
