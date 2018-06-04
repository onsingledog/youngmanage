package com.example.youngmanager.common.service;

import com.example.youngmanager.common.entity.User;
import com.example.youngmanager.common.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2018/6/4.
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;


    public List<User> getUsers(User user){

        return userMapper.getUsers(user);
    }
}
