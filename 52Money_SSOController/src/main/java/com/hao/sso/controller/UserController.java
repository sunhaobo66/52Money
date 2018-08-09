package com.hao.sso.controller;

import com.hao.core.util.CookieUtil;
import com.hao.core.vo.R;
import com.hao.domain.user.User;
import com.hao.sso.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class UserController {

    //创建日志对象
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService service;
    //注册
    @RequestMapping("/user")
    public R save(User user){
        logger.info("新增用户:",user);
        return service.save(user);
    }

    //查询
    @GetMapping("/users")
    public List<User> queryAll(){
        return service.query();
    }


    //登录
    @PostMapping("/userlogin")
    public R login(String name, String password,HttpServletRequest request, HttpServletResponse response){
        System.out.println(name+password);
        R r =service.ssoLogin(name,password);
        if(r.getCode()==0){
            CookieUtil.setCK(response,"userauth",r.getMsg());
            request.getSession().setAttribute(" user",r.getData());
        }
        return r;
    }
    //检查登录ogin
    @GetMapping("/usercheck")
    public R checkL(HttpServletRequest request,HttpServletResponse response){
        String tk=CookieUtil.getCk(request,"userauth");
        R r= service.ssoCheck(tk);
        if(r.getCode()!=0){
            CookieUtil.delCK(response,"userauth");
        }
        return r;
    }
    //注销
    @GetMapping("/userout")
    public R loginout(HttpServletRequest request,HttpServletResponse response){
        String tk=CookieUtil.getCk(request,"userauth");
        R r= service.loginOut(tk);
        if(r.getCode()==0){
            CookieUtil.delCK(response,"userauth");
        }
        return r;
    }
}
