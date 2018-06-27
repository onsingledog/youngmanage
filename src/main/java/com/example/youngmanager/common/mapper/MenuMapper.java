package com.example.youngmanager.common.mapper;


import com.example.youngmanager.common.entity.Menu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuMapper {

    /**
     * 获取菜单的总数量
     * @param menu
     * @return
     */
    int getMenuTotal(Menu menu);

    /**
     * 获取列表数据
     * @param menu
     * @return
     */
    List<Menu> getMenuList(Menu menu);

    /**
     * 获取用户对应的菜单信息
     * @param menuLevel
     * @param userId
     * @return
     */
    List<Menu> getUserMenuData(@Param("menuLevel") String menuLevel, @Param("userId") String userId);

    int deleteByPrimaryKey(String menuId);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(String menuId);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);
}