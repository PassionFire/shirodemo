package com.shirodemo.realm;

import com.shirodemo.bean.User;
import com.shirodemo.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了授权");

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermission("user:add");
        //获取当前对象
        Subject subject = SecurityUtils.getSubject();
        //取出当前用户数据
        User currentUser = (User)subject.getPrincipal();
        //对用户设置权限
        info.addStringPermission(currentUser.getPerms());
        return info;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("执行了认证");

        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
        //连接真实数据库
        User user = userService.findUserByName(userToken.getUsername());
        if(user ==null){
            return null;//抛出异常，用户不存在
        }
        //获取当前对象
        Subject currentSubject = SecurityUtils.getSubject();
        //拿到当前对象session
        Session session = currentSubject.getSession();
        //将用户放到session中
        session.setAttribute("loginUser",user);
        //密码,shiro做
        return new SimpleAuthenticationInfo(user,user.getPwd(),"");
    }
}
