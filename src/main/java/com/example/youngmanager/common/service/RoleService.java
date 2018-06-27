package com.example.youngmanager.common.service;

import com.example.youngmanager.common.entity.LayModel;
import com.example.youngmanager.common.entity.ResParams;
import com.example.youngmanager.common.entity.Role;
import com.example.youngmanager.common.entity.User;
import com.example.youngmanager.common.mapper.RoleMapper;
import com.example.youngmanager.common.util.CommonUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/6/21.
 */
@Service
public class RoleService {

    private Logger logger = LoggerFactory.getLogger(RoleService.class);

    @Autowired
    private RoleMapper roleMapper;

    @Transactional
    public int deleteRole(String id){
        return roleMapper.deleteByPrimaryKey(id);
    }

    public Role getRoleById(String id){
        return roleMapper.selectByPrimaryKey(id);
    }

    /**
     * 修改role
     * @param role
     */
    @Transactional
    public ResParams updateRole(Role role){
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        Role oldRole = this.getRoleById(role.getId());
        if(oldRole==null )
            return new ResParams("-1","没有找到相对应的数据");
        oldRole.setRoleCode(role.getRoleCode());
        oldRole.setRoleName(role.getRoleName());
        oldRole.setComment(role.getComment());
        oldRole.setLastUpdateBy(user.getId());
        oldRole.setLastUpdateDate(new Date());
        roleMapper.updateByPrimaryKey(oldRole);
        return new ResParams("1","修改成功");
    }

    /**
     * 添加一个role
     * @param role
     */
    @Transactional
    public void addRole(Role role){
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        Date d = new Date();
        role.setCreateBy(user.getId());
        role.setCreateDate(d);
        role.setLastUpdateBy(user.getId());
        role.setLastUpdateDate(d);
        role.setId(CommonUtil.getUuid());
        roleMapper.insert(role);
    }

    public LayModel getRoles(Role role,int pageNum,int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Role> list = roleMapper.getRoles(role);
        PageInfo<Role> pageInfo = new PageInfo<>(list);
        return new LayModel(pageInfo);
    }

}
