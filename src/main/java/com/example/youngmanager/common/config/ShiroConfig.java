package com.example.youngmanager.common.config;

import com.example.youngmanager.common.realm.MyRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * Created by Administrator on 2018/6/6.
 */
@Configuration
public class ShiroConfig {


    /**
     *
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        //设置securityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        shiroFilterFactoryBean.setLoginUrl("/user/login");
        shiroFilterFactoryBean.setSuccessUrl("/main");
//        shiroFilterFactoryBean.setUnauthorizedUrl("/");

        //过滤链定义，从上向下顺序执行，一般讲/**放到最下面
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/file/**","anon");//静态文件不过滤
        filterChainDefinitionMap.put("/druid/**","anon");
        filterChainDefinitionMap.put("/ajaxLogin","anon");
        filterChainDefinitionMap.put("/logout","logout");
        filterChainDefinitionMap.put("/add", "perms[权限添加]");
        filterChainDefinitionMap.put("/**", "authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;
    }

    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myRealm());
        return securityManager;
    }

    @Bean
    public AuthorizingRealm myRealm(){

        return new MyRealm();
    }

}
