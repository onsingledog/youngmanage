package com.example.youngmanager.common.service;

import com.example.youngmanager.common.entity.User;
import com.example.youngmanager.common.mapper.UserMapper;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Administrator on 2018/6/4.
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 用户注册
     * @param user
     */
    public void register(User user){
        user.setId(UUID.randomUUID().toString().replaceAll("-",""));
        userMapper.insert(user);
    }

    /**
     * 获取用户的权限码集合
     * @param userId
     * @return
     */
    public Set<String> getUserPermission(String userId){
        Set<String> userPermission = new TreeSet<>();

        return userPermission;
    }

    /**
     * 获取用户角色码集合
     * @param userId
     * @return
     */
    public Set<String> getUserRole(String userId){
        Set<String> userRoleSet = new TreeSet<>();

        return userRoleSet;
    }

    /**
     * 获取登录的用户
     * @param loginname
     * @param password
     * @return
     */
    public List<User> getLoginUsers(String loginname, String password){
        return userMapper.getLoginUser(loginname,password);
    }

    public List<User> getUsers(User user){

        return new ArrayList<>();
    }
}
