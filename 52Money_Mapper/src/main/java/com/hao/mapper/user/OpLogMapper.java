package com.hao.mapper.user;

import com.hao.domain.user.LoginLog;
import com.hao.domain.user.OpLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import java.util.List;

//审核日志表
public interface OpLogMapper {
    @Insert("insert into t_loginlog (ip,createtime,uid,msg) values(#{ip},now(),#{uid},#{msg})")
   @Options(useGeneratedKeys = true,keyProperty = "id")
    int insert(OpLog opLog);

    @Select("select id,ip,createtime,uid,msg from t_loginlog where id=#{id}")
    @ResultType(LoginLog.class)
    List<OpLog> selectByTypey(Integer type);
}
