package com.hao.mapper.admin;

import com.hao.domain.admin.SysUserRole;

import java.util.List;


public interface SysUserRoleMapper {
    int insert(SysUserRole record);
    SysUserRole selectByPrimaryKey(Long id);
    int updateByPrimaryKey(SysUserRole record);
    List<Long> queryRoleIdList(long userId);
    Integer deleteByUserId(long userId);
    Integer deleteByRoleIds(Long[] roleIds);
    Integer deleteByUserIds(Long[] userIds);
}
