package com.example.youngmanager.common.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * Created by Administrator on 2018/6/7.
 */
public class CommonUtil {



    /**
     * ShiroSession设置
     * 使用时直接用HttpSession.getAttribute(key)就可以取到
     * @Description: TODO
     * @param key
     * @param value
     * @return void
     * @author nwx
     * @date 2016-6-21
     */
    public static void setSession(Object key, Object value) {
        Subject currentUser = SecurityUtils.getSubject();
        if (null != currentUser) {
            Session session = currentUser.getSession();
            if (null != session) {
                session.setAttribute(key, value);
            }
        }
    }

    /**
     * 获取session
     * @param key
     * @return
     */
    public static Object getSession(Object key){

        Subject currentUser = SecurityUtils.getSubject();
        if(null != currentUser){
            Session session = currentUser.getSession();
            if(null != session){
                return session.getAttribute(key);
            }
        }
        return null;
    }

}
