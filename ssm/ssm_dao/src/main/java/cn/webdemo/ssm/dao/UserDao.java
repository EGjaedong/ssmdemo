package cn.webdemo.ssm.dao;

import cn.webdemo.ssm.domain.Role;
import cn.webdemo.ssm.domain.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 63042 on 2019/6/30.
 *
 */
//@Repository("userDao")
public interface UserDao {
    @Select("select * from users where username = #{username} ")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles", column = "id", javaType = java.util.List.class, many = @Many(select = "cn.webdemo.ssm.dao.RoleDao.findRoleByUserId"))
    })
    public UserInfo findByUserName(String username) throws Exception ;

    @Select("select * from users")
    List<UserInfo> findAll() throws Exception ;

    @Insert("insert into users(email, username, password, phoneNum, status) values(#{email}, #{username}, #{password}, #{phoneNum}, #{status})")
    void save(UserInfo userInfo) throws Exception ;

    @Select("select * from users where id = #{userId}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles", column = "id", javaType = java.util.List.class, many = @Many(select = "cn.webdemo.ssm.dao.RoleDao.findRoleByUserId"))
    })
    UserInfo findById(int userId) throws Exception ;

    @Select("select * from role where id not in(select roleId from users_role where userId = #{userId})")
    List<Role> findOtherRoles(int userId) throws Exception;

    @Select("insert into users_role(userId, roleId) values(#{userId}, #{roleId})")
    void addRoleToUser(@Param("userId") int userId, @Param("roleId") int roleId);
}
