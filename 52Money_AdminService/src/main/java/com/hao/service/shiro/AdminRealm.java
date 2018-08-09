package com.hao.service.shiro;

import com.hao.core.shiro.ShiroUtil;
import com.hao.domain.admin.SysUser;
import com.hao.service.admin.SysMenuService;
import com.hao.service.admin.SysRoleService;
import com.hao.service.admin.SysUserService;
import org.apache.shiro.authc.*;

import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class AdminRealm extends AuthorizingRealm {
        @Autowired
        private SysUserService sysUserService;

        @Autowired
        private  SysMenuService sysMenuService;

        @Autowired
        private SysRoleService sysRoleService;

        //实现用户权限的数据提供 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SysUser user = (SysUser) principalCollection.getPrimaryPrincipal();
        //获取用户主键值
        Long userId = user.getUserId();
        //分配权限
        //分配角色列表
        List<String> roleList = Arrays.asList("admin","user");
       //权限信息
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //分配角色权限
        info.addRoles(roleList);
        //getqueryAllPerms 获取用户所有的权限
        List<String> permissions = sysMenuService.getUserPermsList(userId);
        for (String perm : permissions){
            System.out.println(perm);
        }
        //分配 资源权限
       info.addStringPermissions(permissions);
        return info;

    }
        //实现用户登录的验证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取用户输入的用户名和密码
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        String password = new String (token.getPassword());
        System.err.println("用户登录信息："+username+"--->"+password);
        //获取数据库的用户信息
        SysUser user = sysUserService.getByUsername(username);

        if(user == null){
            //用户名不存在
            throw new UnknownAccountException("用户名不存在");
        }else if (!Objects.equals(password,user.getPassword())){
            System.err.println("throws:"+user.toString());
            throw  new IncorrectCredentialsException("密码不正确");
        }else {
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user,password,getName());

            System.out.println("会话2:" + ShiroUtil.getSubject().getSession());
            ShiroUtil.getSubject().getSession().setAttribute("sysuser",user);

            return info;
        }
    }
}
