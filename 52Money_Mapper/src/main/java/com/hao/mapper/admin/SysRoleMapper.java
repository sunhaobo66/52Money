package com.hao.mapper.admin;
import com.hao.domain.admin.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface SysRoleMapper {
    int insert(SysRole record);
    SysRole selectByPrimaryKey(Long roleId);
    int updateByPrimaryKey(SysRole record);
    List<SysRole> queryByPage(@Param("index") int index, @Param("count") int count);
    Long queryCount();
    List<SysRole> queryAll();
    List<String> selectRoleNameList(Long userId);
    int deleteBatch(Long[] roleIds);
}
