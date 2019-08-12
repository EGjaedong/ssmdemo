package cn.webdemo.ssm.dao;

import cn.webdemo.ssm.domain.Traveller;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 63042 on 2019/6/25.
 *
 */
//@Repository("travellerDao")
public interface TravellerDao {
    @Select("select * from traveller where id in (select travellerId from order_traveller where orderId = #{ordersId})")
    public List<Traveller> findByOrdersId(int ordersId) throws Exception;
}
