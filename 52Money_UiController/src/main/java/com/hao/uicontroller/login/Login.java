package com.hao.uicontroller.login;


import com.alibaba.fastjson.JSON;
import com.hao.core.redis.JedisUtil;
import com.hao.core.util.CookieUtil;
import com.hao.domain.user.User;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
//@RestController
public class Login {
    public static User getU(JedisUtil jedisUtil, HttpServletRequest request){
        String json = jedisUtil.getStr("usertoken:" + CookieUtil.getCk(request,"userauth"));
        return JSON.parseObject(json,User.class);
    }
}
