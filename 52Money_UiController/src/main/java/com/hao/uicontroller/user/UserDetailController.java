package com.hao.uicontroller.user;

import com.hao.core.redis.JedisUtil;
import com.hao.core.vo.R;
import com.hao.domain.user.User;
import com.hao.domain.user.UserDetail;
import com.hao.service.user.UserDetailService;
import com.hao.uicontroller.login.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

@RestController
public class UserDetailController {
    @Autowired
    private UserDetailService service;

    @Autowired
    private JedisUtil jedisUtil;

    //初始化
    @GetMapping("detailinit")
    public R init(HttpServletRequest request){
        User user = Login.getU(jedisUtil,request);
        System.out.println("**********User:" + user);
        return service.realNameAuth(user.getUid());
    }
    //修改
    @PostMapping("/detailupdate.do")
    public R update(UserDetail detail,HttpServletRequest request){
        User user = Login.getU(jedisUtil,request);

//        detail.setUid(((User)request.getSession().getAttribute("user")).getId());

detail.setUid(user.getId());

        System.out.println("%%%%%%%%%%%User:" + detail);
        return service.update(detail);
    }

    //查询
    @GetMapping("detailall")
    public List<UserDetail> queryAll(int flag){
        return service.queryByFlag(flag);
    }
}
