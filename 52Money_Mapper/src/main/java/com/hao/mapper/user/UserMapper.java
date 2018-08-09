package com.hao.mapper.user;

import com.hao.domain.user.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {

    @Insert("insert into t_user(username,password,phone,uid) values(#{username},#{password},#{phone},#{uid})")
    int insert(User user);

    @Select("select id,username,password,phone,uid from t_user limit #{index},#{count}")
    @ResultType(User.class)
    List<User> queryPage(@Param("index") int index, @Param("count") int count);

    //登录查询
    @Select("select id,username,password,phone,uid from t_user where username=#{name} or phone=#{name}")
    @ResultType(User.class)
    User queryBy(String name);
}