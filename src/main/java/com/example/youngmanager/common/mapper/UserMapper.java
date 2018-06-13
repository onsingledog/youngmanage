package com.example.youngmanager.common.mapper;

import com.example.youngmanager.common.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    /**
     * 获取登录的用户
     * @param loginname
     * @param password
     * @return
     */
    List<User> getLoginUser(@Param("loginname") String loginname,@Param("password") String password);

    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}