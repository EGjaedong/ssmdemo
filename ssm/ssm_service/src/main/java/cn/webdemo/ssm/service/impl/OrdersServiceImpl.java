package cn.webdemo.ssm.service.impl;

import cn.webdemo.ssm.dao.OrdersDao;
import cn.webdemo.ssm.domain.Orders;
import cn.webdemo.ssm.service.OrdersService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by 63042 on 2019/6/25.
 *
 */
@Service("orderService")
@Transactional
public class OrdersServiceImpl implements OrdersService {
    @Autowired
    private OrdersDao ordersDao;

    @Override
    public List<Orders> findAll(int page, int size) throws Exception {
        // 在真正执行sql之前，调用PageHelper.startPage 方法，中间不能有其他代码存在
        // 第一个参数时pageNum，即当前页码值；第二个参数为pageSize，即每页显示条目数。
        PageHelper.startPage(page, size);
        return ordersDao.findAll();
    }

    @Override
    public Orders findById(int ordersId) throws Exception {
        return ordersDao.findById(ordersId);
    }
}
