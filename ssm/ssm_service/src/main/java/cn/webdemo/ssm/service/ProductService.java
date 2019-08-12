package cn.webdemo.ssm.service;

import cn.webdemo.ssm.domain.Product;

import java.util.List;

/**
 * Created by 63042 on 2019/6/24.
 *
 */
public interface ProductService {
    public List<Product> findAll() throws Exception ;

    public void save(Product product);
}
