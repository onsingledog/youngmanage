package com.example.youngmanager.common.service;

import com.example.youngmanager.common.entity.LayModel;
import com.example.youngmanager.common.entity.Menu;
import com.example.youngmanager.common.mapper.MenuMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * Created by Administrator on 2018/6/27.
 */
@Service
public class MenuService {

    @Autowired
    private MenuMapper menuMapper;

    /**
     * 获取菜单的总数量
     * @param menu
     * @return
     */
    public int getMenuTotal(Menu menu){
        return menuMapper.getMenuTotal(menu);
    }

    /**
     * 获取所有父级菜单
     * @return
     */
    public List<Menu> getParentMenu(){
        Menu menu = new Menu();
        menu.setMenuLevel("1");
        return menuMapper.getMenuList(menu);
    }

    /**
     * 获取列表数据
     * @param menu
     * @return
     */
    public LayModel<Menu> getMenuList(Menu menu,int page,int limit){
        PageHelper.startPage(page,limit);
        List<Menu> list = menuMapper.getMenuList(menu);
        PageInfo<Menu> pageInfo = new PageInfo<>(list);
        return new LayModel<>(pageInfo);
    }

}
