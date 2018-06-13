package com.example.youngmanager.common.controller;

import com.example.youngmanager.common.entity.ResParams;
import com.example.youngmanager.common.entity.User;
import com.example.youngmanager.common.service.UserService;
import com.example.youngmanager.common.util.Constants;
import com.example.youngmanager.common.util.IpUtil;
import com.example.youngmanager.common.util.Md5Util;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md2Hash;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.security.provider.MD5;

import javax.servlet.http.HttpServletRequest;
import javax.xml.crypto.Data;
import java.util.Date;
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

    /**
     * 跳转到主页
     * @return
     */
    @GetMapping("/main")
    public String toMain(){
        return BASE + "/main";
    }

    /**
     * 退出
     * @return
     */
    @GetMapping("/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(){
        return BASE + "/login";
    }

    /**
     * 用户请求登录方法
     * @param loginName
     * @param password
     * @return
     */
    @PostMapping("/ajaxLogin")
    @ResponseBody
    public ResParams login(String loginName,String password){
        ResParams r;
        try{
            if(StringUtils.isBlank(loginName) || StringUtils.isBlank(password))
                return new ResParams("-1","请输入用户名或密码！");
            UsernamePasswordToken token = new UsernamePasswordToken(loginName,Md5Util.md5(password));
            SecurityUtils.getSubject().login(token);
            r = new ResParams("1","登录成功");
        } catch (ShiroException e){
            r = new ResParams("-1",e.getMessage());
        } catch (Exception e){
            r = new ResParams("-1","登录失败");
            e.printStackTrace();
        }

        return r;
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
    public ResParams register(User user, @RequestParam("password2") String password2, HttpServletRequest request){
        Subject subject = SecurityUtils.getSubject();
        try{
            if(!user.getPassword().equals(password2))
                return new ResParams("-1","两次输入密码不一致");

            Date d = new Date();
            user.setState(Constants.ENABLE);
            user.setCreateDate(d);
            user.setLastUpdateDate(d);
            user.setLastLoginDate(d);
            user.setLastLoginIp(IpUtil.getIpAddr(request));
            user.setComment("普通注册用户");
            user.setPassword(Md5Util.md5(user.getPassword()));
            userService.register(user);
            UsernamePasswordToken token = new UsernamePasswordToken(user.getTelephone(),user.getPassword());
            subject.login(token);
        }catch (Exception e){
            subject.logout();
            e.printStackTrace();
            return new ResParams("-1","注册失败");
        }
        return new ResParams("1","注册成功");
    }

}
