package com.example.youngmanager.common.controller;

import com.example.youngmanager.common.entity.LayModel;
import com.example.youngmanager.common.entity.ResParams;
import com.example.youngmanager.common.entity.Role;
import com.example.youngmanager.common.entity.User;
import com.example.youngmanager.common.service.RoleService;
import com.example.youngmanager.common.util.CommonUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2018/6/21.
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    private final String BASE = "/common/role";

    @Autowired
    private RoleService roleService;

    @PostMapping("/deleteRole")
    @ResponseBody
    public ResParams deleteRole(String id){
        try{
            if(StringUtils.isBlank(id))
                return new ResParams("-1","删除失败，请选择要删除的数据");
            roleService.deleteRole(id);
        }catch (Exception e){
            e.printStackTrace();
            return new ResParams("-1","删除失败");
        }

        return new ResParams("1","删除成功");
    }

    /**
     * 修改
     * @param role
     * @return
     */
    @PostMapping("/updateRole")
    @ResponseBody
    public ResParams updateRole(Role role){
        try{
            if(StringUtils.isBlank(role.getId()))
                return new ResParams("-1","没有数据");
            if(StringUtils.isBlank(role.getRoleCode()))
                return new ResParams("-1","请输入角色编码");
            if(StringUtils.isBlank(role.getRoleName()))
                return new ResParams("-1","请输入角色名称");
            return roleService.updateRole(role);
        }catch (Exception e){
            e.printStackTrace();
            return new ResParams("-1","参数错误或系统异常");
        }
    }

    @GetMapping("/updateRole")
    public String updateRole(Model model,String id){
        model.addAttribute("role", roleService.getRoleById(id));
        return BASE + "/updateRole";
    }

    /**
     * 添加role
     * @param role
     * @return
     */
    @PostMapping("/addRole")
    @ResponseBody
    public ResParams addRole(Role role){
        try{
            if(StringUtils.isBlank(role.getRoleCode()))
                return new ResParams("-1","请输入角色编码");
            if(StringUtils.isBlank(role.getRoleName()))
                return new ResParams("-1","请输入角色名称");

            roleService.addRole(role);
        }catch (Exception e){
            e.printStackTrace();
            return new ResParams("-1","参数错误或系统异常");
        }

        return new ResParams("1","添加成功");
    }

    @GetMapping("/addRole")
    public String addRole(){
        return BASE + "/addRole";
    }

    @GetMapping("/role")
    public String role(Model model,HttpServletRequest request){
        System.out.println(request.getSession().getMaxInactiveInterval());
        return BASE + "/role";
    }

    @PostMapping("/getRoles")
    @ResponseBody
    public LayModel getRoles(Role role,int page,int limit){
        if(page==0 || limit==0){
            page=1;
            limit=10;
        }
        return roleService.getRoles(role,page,limit);
    }

}
