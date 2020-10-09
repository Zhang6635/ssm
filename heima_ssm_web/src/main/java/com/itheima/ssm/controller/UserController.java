package com.itheima.ssm.controller;

import com.itheima.ssm.domain.Role;
import com.itheima.ssm.domain.UserInfo;
import com.itheima.ssm.service.IRoleService;
import com.itheima.ssm.service.IUserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @RequestMapping("/findAll.do")
    public ModelAndView findALL() throws Exception {

        List<UserInfo> users = userService.findALl();
        ModelAndView mv = new ModelAndView();
        mv.addObject("userList", users);
        mv.setViewName("user-list");
        return mv;
    }

    @RequestMapping("/save.do")
    public String save(UserInfo user) throws Exception{
        userService.save(user);
        return "redirect:findAll.do";
    }

    @RequestMapping("/findById.do")
    public ModelAndView findById(String id) throws Exception{
        ModelAndView mv = new ModelAndView();
       UserInfo userInfo = userService.findById(id);
        mv.addObject("user",userInfo);
        mv.setViewName("user-show");
        return mv;

    }

    @RequestMapping("/findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole(String id) throws Exception{
        UserInfo user = userService.findById(id);
        List<Role> roleList = roleService.findOtherRole(id);
        ModelAndView mv = new ModelAndView();
        mv.addObject("user",user);
        mv.addObject("roleList",roleList);
        mv.setViewName("user-role-add");
        return mv;
    }

    @RequestMapping("/addRoleToUser")
    public String addRoleToUser(@RequestParam(name="userId",required = true) String userId,@RequestParam(name="ids",required = true) String[] ids) throws  Exception{

        userService.addRoleToUser(userId,ids);
        return "redirect:findAll.do";
    }

}
