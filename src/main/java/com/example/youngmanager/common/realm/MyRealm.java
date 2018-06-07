package com.example.youngmanager.common.realm;

import com.example.youngmanager.common.entity.User;
import com.example.youngmanager.common.service.UserService;
import com.example.youngmanager.common.util.CommonUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Administrator on 2018/6/6.
 */
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * 用户分配角色和权限
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        User user = (User)SecurityUtils.getSubject().getPrincipal();

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addStringPermissions(userService.getUserPermission(user.getId()));
        simpleAuthorizationInfo.addRoles(userService.getUserRole(user.getId()));

        return simpleAuthorizationInfo;
    }

    /**
     * 用户登录验证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        String username = token.getUsername();
        String password = new String(token.getPassword());

        List<User> users = userService.getLoginUsers(username,password);
        if(users==null || users.size()==0)
            throw new AccountException("用户名或密码错误");
        User user = users.get(0);
        if(user.userState())
            throw new DisabledAccountException("用户状态为禁止登录状态");
        CommonUtil.setSession("_User",user);
        return new  SimpleAuthenticationInfo(user, username, password);
    }

}
