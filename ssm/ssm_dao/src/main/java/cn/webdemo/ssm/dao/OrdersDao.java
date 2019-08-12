package cn.webdemo.ssm.dao;

import cn.webdemo.ssm.domain.Member;
import cn.webdemo.ssm.domain.Orders;
import cn.webdemo.ssm.domain.Product;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 63042 on 2019/6/25.
 *
 */
//@Repository("ordersDao")
public interface OrdersDao {
    /**
     * 查询所有
     * @return
     * @throws Exception
     */
    @Select("select * from orders")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "orderNum", column = "orderNum"),
            @Result(property = "orderTime", column = "orderTime"),
            @Result(property = "orderStatus", column = "orderStatus"),
            @Result(property = "peopleCount", column = "peopleCount"),
            @Result(property = "payType", column = "payType"),
            @Result(property = "orderDesc", column = "orderDesc"),
            @Result(property = "product", column = "productId", javaType = Product.class, one = @One(select = "cn.webdemo.ssm.dao.ProductDao.findById"))
    })
    public List<Orders> findAll() throws Exception ;

    /**
     * 查询详情，涉及到多表操作，有多对多和多对一（MyBatis认为多对一是一对一）
     * @param ordersId
     * @return
     * @throws Exception
     */
    @Select("select * from orders where id = #{ordersId} ")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "orderNum", column = "orderNum"),
            @Result(property = "orderTime", column = "orderTime"),
            @Result(property = "orderStatus", column = "orderStatus"),
            @Result(property = "peopleCount", column = "peopleCount"),
            @Result(property = "payType", column = "payType"),
            @Result(property = "orderDesc", column = "orderDesc"),
            @Result(property = "product", column = "productId", javaType = Product.class, one = @One(select = "cn.webdemo.ssm.dao.ProductDao.findById")),
            @Result(property = "member", column = "memberId", javaType = Member.class, one = @One(select = "cn.webdemo.ssm.dao.MemberDao.findById")),
            @Result(property = "travellers", column = "id", javaType = java.util.List.class, many = @Many(select = "cn.webdemo.ssm.dao.TravellerDao.findByOrdersId"))
    })
    public Orders findById(int ordersId) throws Exception;
}
