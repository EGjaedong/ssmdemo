package cn.webdemo.ssm.service;

import cn.webdemo.ssm.domain.Orders;

import java.util.List;

/**
 * Created by 63042 on 2019/6/25.
 *
 */
public interface OrdersService {
    public List<Orders> findAll(int page, int size) throws Exception;

    public Orders findById(int ordersId) throws Exception;
}
