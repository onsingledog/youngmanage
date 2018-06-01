package com.example.youngmanager.common.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2018/6/1.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private String BASE = "/common/user";

    @GetMapping("/login")
    public String login(){

        return BASE + "/login";
    }

}
