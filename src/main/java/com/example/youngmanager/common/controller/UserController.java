package com.example.youngmanager.common.controller;

import com.example.youngmanager.common.entity.ResParams;
import com.example.youngmanager.common.entity.User;
import com.example.youngmanager.common.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 2018/6/1.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private String BASE = "/common/user";

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/getUsers")
    @ResponseBody
    public List<User> getUsers(){
        return userService.getUsers(new User());
    }

    @GetMapping("/login")
    public String login(){
        return BASE + "/login";
    }

    /**
     * 跳转到主页
     * @return
     */
    @GetMapping("/main")
    public String toMain(){

        return BASE + "/main";
    }

    /**
     * 用户注册
     * @return
     */
    @GetMapping("/register")
    public String register(){
        return BASE + "/register";
    }

    /**
     * 注册方法
     * @param user
     * @return
     */
    @ResponseBody
    @PostMapping("/register")
    public ResParams register(User user){
        try{

        }catch (Exception e){
            return new ResParams("-1","注册失败");
        }
        return new ResParams("1","注册成功");
    }


    /**
     * 用户请求登录方法
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/ajaxLogin")
    public ResParams login(String username,String password){

        ResParams r;
        try{
            UsernamePasswordToken token = new UsernamePasswordToken(username,password);
            SecurityUtils.getSubject().login(token);
            r = new ResParams("1","登录成功");
        }catch (Exception e){
            e.printStackTrace();
            r = new ResParams("-1","登录失败");
        }

        return r;
    }

}
