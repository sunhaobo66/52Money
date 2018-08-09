package com.hao.sso.provider;

import com.alibaba.fastjson.JSON;
import com.hao.core.redis.JedisUtil;
import com.hao.core.util.ExecuteUtils;
import com.hao.core.util.IdGenerator;
import com.hao.core.vo.R;
import com.hao.domain.user.User;
import com.hao.mapper.user.UserMapper;
import com.hao.sso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
public class UserProvider implements UserService {

    @Autowired
    private UserMapper mapper;

    @Autowired
    private IdGenerator idGenerator;

    @Autowired
    private JedisUtil jedisUtil;

    @Override
    public R save(User user) {
        return ExecuteUtils.getR(mapper.insert(user),"新增用户");
    }
    //查询所有
    @Override
    public List<User> query() {
        return mapper.queryPage(0,20);
    }
    //查询
    @Override
    public User queryBy(String name) {
        User user = mapper.queryBy(name);
        return user;
    }

    @Override
    public R ssoLogin(String name, String password) {
        //没有Token  第一次登录
        User user=mapper.queryBy(name);
        if(user!=null){
            if(Objects.equals(password,user.getPassword())) {
                //登录成功
                //生成唯一令牌
                long tk=idGenerator.nextId();
                //令牌存储到Redis  key:usertoken+令牌值 value:登录用户信息的json
                jedisUtil.addStr("usertoken:"+tk,JSON.toJSONString(user));
                //设置有效期  默认30分钟
                jedisUtil.expire("usertoken:"+tk,TimeUnit.MINUTES,30);
                //返回结果
                return new R(0,tk+"",user);
            }else {
                //密码不正常
                return R.setError("密码不正确");
            }
        }else{
            //用户名不存在
            return R.setError("用户名不存在");
        }
    }

    @Override
    public R ssoCheck(String token) {
        if(jedisUtil.isKey("usertoken:"+token)){
            //存在就刷新时间
            jedisUtil.expire("usertoken:"+token,TimeUnit.MINUTES,30);
            return new R(0,"存在",JSON.parseObject(jedisUtil.getStr("usertoken:"+token),User.class));
        }else{
            return R.setError("不存在");
        }
    }

    @Override
    public R loginOut(String token) {
        //移除Redis
        jedisUtil.delKey("usertoken:"+token);
        return R.setOK("注销成功");
    }
}
