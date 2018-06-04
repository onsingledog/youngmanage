package com.example.youngmanager.common.controller;

import com.example.youngmanager.common.entity.User;
import com.example.youngmanager.common.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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

}
