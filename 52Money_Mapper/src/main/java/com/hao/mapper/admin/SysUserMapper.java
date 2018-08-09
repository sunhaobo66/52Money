package com.hao.mapper.admin;

import com.hao.domain.admin.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface SysUserMapper {
    int insert(SysUser record);
    SysUser selectByPrimaryKey(Long userId);
    int updateByPrimaryKey(SysUser record);
    SysUser queryByUserName(String username);
    List<SysUser> selectByPage(@Param("index") int index, @Param("count") int count);
    long selectCount();
    int deleteBatch(Long[] uids);
    String queryPassword(long uid);

}
