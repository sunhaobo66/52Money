package com.hao.mapper.user;

import com.hao.domain.user.LoginLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import java.util.List;

//登录日志表
public interface LoginLogMapper {
    @Insert("insert into t_loginlog(ip,createtime,uid,msg)values(#{ip},#{createtime},#{uid},#{msg})")
    //获取Id自动主键
    @Options(useGeneratedKeys = true,keyProperty ="id")
    int insert(LoginLog loginLog);

    @Select("select id,ip,createtime,uid,msg from t_loginlog where id=#{id}")
    @ResultType(LoginLog.class)
    LoginLog selectById(Integer id);

    @Select("select id,ip,createtime,uid,msg from t_loginlog")
    @ResultType(LoginLog.class)
    List<LoginLog> selectAll();
}
