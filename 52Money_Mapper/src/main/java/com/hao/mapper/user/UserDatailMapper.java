package com.hao.mapper.user;


import com.hao.domain.user.UserDetail;
import org.apache.ibatis.annotations.*;

import java.util.List;

//用户详情 实名认证
public interface UserDatailMapper {
    //修改通过用过Id进行修改
    @Update("update t_userdetail set realname=#{realname},sex=#{sex},idNumber=#{idNumber},birthdate=#{birthdate},address=#{address},idimage1=#{idimage1},idimage2=#{idimage2},flag=1 where uid=#{uid}")
    int updateById(UserDetail detail);

    //修改Flag标记
    @Update("update t_userdetail set flag=#{flag},where id=#{id}")
    int updateId(@Param("flag") int flag,@Param("id") int id);

    //新增
    @Insert("insert into t_userdetail(uid,realname,sex,idNumber,birthDate,address,idimage1,idimage2,createtime,flag) value(#{uid},null,null ,null ,null,null,null ,null,now(),0)")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    int insert(int uid);


    //查询通过用户ID
    @Select("select id,uid,realname,sex,idNumber,birthDate,address,idimage1,idimage2,createtime,flag,from t_userdeteil where uid=#{uid}")
    @ResultType(UserDetail.class)
    UserDetail selectByUid(int uid);

    //查询通过flag标记
    @Select("select id,uid,realname,sex,idNumber,birthDate,address,idimage1,idimage2,createtime,flag,from t_userdeteil where flag=#{flag}")
    @ResultType(UserDetail.class)
    List<UserDetail> selectByFlag(int flag);

    //分页查询
    @Select("select + from t_userdetail limit #{index},#{count}")
    @ResultType(UserDetail.class)
    List<UserDetail> queryByPage(@Param("index") int index,@Param("count") int count);

    //查询数量
    @Select("select count(*) from t_userdetail")
    @ResultType(Long.class)
    long queryCount();





}
