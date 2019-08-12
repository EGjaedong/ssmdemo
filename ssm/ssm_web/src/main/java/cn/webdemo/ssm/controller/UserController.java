package cn.webdemo.ssm.controller;

import cn.webdemo.ssm.domain.Role;
import cn.webdemo.ssm.domain.UserInfo;
import cn.webdemo.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by 63042 on 2019/7/16.
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/addRoleToUser.do")
    // 为了记录日志，参数类型改成包装类型
    public String addRoleToUser(@RequestParam(name = "userId", required = true)Integer userId,
                                @RequestParam(name = "ids", required = true)Integer[] roleIds){
        userService.addRoleToUser(userId, roleIds);
        return "redirect:findUserByIdAndAllRole.do";
    }

    /**
     * 根据用户id查询当前用户有哪些可添加的角色
     * @param userId
     * @return
     */
    @RequestMapping("/findUserByIdAndAllRole.do")
    @PreAuthorize("authentication.principal.username == 'tom'") // 表示只有tom 才可以使用此方法
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id", required = true) Integer userId) throws Exception {
        ModelAndView mv = new ModelAndView();
        UserInfo user = userService.findById(userId);
        List<Role> otherRoles = userService.findOtherRoles(userId);
        mv.addObject("user", user);
        mv.addObject("roleList", otherRoles);
        mv.setViewName("user-role-add");
        return mv;
    }

    @RequestMapping("/findAll.do")
    @PreAuthorize("hasRole('ROLE_ADMIN')") // SPEL表达式权限控制，表示只有ROLE_ADMIN才可以访问此方法
    public ModelAndView findAll() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        List<UserInfo> userList = userService.findAll();
        modelAndView.addObject("userList", userList);
        modelAndView.setViewName("user-list");
        return modelAndView;
    }

    @RequestMapping("/save.do")
    public String save(UserInfo userInfo) throws Exception {
        userService.save(userInfo);
        return "redirect:findAll.do";
    }

    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id", required = true)Integer userId) throws Exception {
        ModelAndView mv = new ModelAndView();
        UserInfo userInfo = userService.findById(userId);
        mv.addObject("user", userInfo);
        mv.setViewName("user-show");
        return mv;
    }
}
