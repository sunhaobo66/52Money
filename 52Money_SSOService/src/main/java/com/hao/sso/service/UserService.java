package com.hao.sso.service;

import com.hao.core.vo.R;
import com.hao.domain.user.User;

import java.util.List;

public interface UserService {
    //新增
    R save(User user);
    //查询所有
    List<User> query();
    //查询
    User queryBy(String name);

    //单点登录之登录
    R ssoLogin(String name,String password);

    //单点登录之检查是否登录
    R ssoCheck(String token);

    //退出
    R loginOut(String token);
}
