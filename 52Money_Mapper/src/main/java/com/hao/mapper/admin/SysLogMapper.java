package com.hao.mapper.admin;

import com.hao.domain.admin.SysConfig;
import com.hao.domain.admin.SysLog;
import com.hao.mapper.provider.SysLogProvider;
import org.apache.ibatis.annotations.*;
import java.util.List;

public interface SysLogMapper {
    @Insert("insert into sys_log(username,operation,method,params,ip,createDate) values(#{username},#{operation},#{method},#{params},#{ip},now())")
    int insert(SysLog log);
    @Select({"<script>","select * from sys_log <where><if test='id>0'>id=#{id}</if></where>","</script>"})
    SysConfig selectById(Long id);
    @SelectProvider(type =SysLogProvider.class,method = "queryByPage")
    List<SysLog> selectByPage(@Param("index") int index, @Param("size") int size);
    @Select("select COUNT(*) from sys_log")
    @ResultType(Long.class)
    long selectCount();

}
