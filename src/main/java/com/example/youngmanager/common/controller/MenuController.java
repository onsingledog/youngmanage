package com.example.youngmanager.common.controller;

import com.example.youngmanager.common.entity.LayModel;
import com.example.youngmanager.common.entity.Menu;
import com.example.youngmanager.common.entity.ResParams;
import com.example.youngmanager.common.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2018/6/26.
 */
@Controller
@RequestMapping("/menu")
public class MenuController {

    private final String BASE = "/common/menu";

    @Autowired
    private MenuService menuService;


    /**
     * 添加菜单功能
     * @param model
     * @return
     */
    @GetMapping("/addMenu")
    public String addMenu(Model model){
        model.addAttribute("oper","add");
        model.addAttribute("parentMenuList", menuService.getParentMenu());
        return BASE + "/menu";
    }

    /**
     * 获取菜单的数量
     * @param parentId
     * @param menuLevel
     * @return
     */
    @PostMapping("/getMenuNum")
    public ResParams getMenuNum(String parentId,String menuLevel){
        if("1".equals(menuLevel)){

        }else if("2".equals(menuLevel)){

        }
        return null;
    }

    @GetMapping("/menuList")
    public String menu(){
        return BASE + "/menuList";
    }

    @ResponseBody
    @PostMapping("/menuList")
    public LayModel<Menu> getMenuList(Menu menu,int page,int limit){
        if(page==0 || limit==0){
            page = 1;
            limit = 10;
        }
        return menuService.getMenuList(menu,page,limit);
    }

}
