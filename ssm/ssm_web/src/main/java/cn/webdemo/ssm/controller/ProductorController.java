package cn.webdemo.ssm.controller;

import cn.webdemo.ssm.domain.Product;
import cn.webdemo.ssm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

/**
 * Created by 63042 on 2019/6/24.
 *
 */
@Controller
@RequestMapping("/product")
public class ProductorController {
    @Autowired
    private ProductService productService;

    @RequestMapping("/findAll.do")
    @RolesAllowed({"ADMIN"}) // JSR250权限控制注解，可以省略ROLE_前缀，本来应该是ROLE_ADMIN
    public ModelAndView findAll() throws Exception{
        ModelAndView mv = new ModelAndView();
        List<Product> ps = productService.findAll();
        mv.addObject("productList",ps);
        mv.setViewName("product-list1");
        return mv;
    }

    @RequestMapping("/save.do")
    public String save(Product product) throws Exception{
        productService.save(product);
        return "redirect:findAll.do";
    }
}
