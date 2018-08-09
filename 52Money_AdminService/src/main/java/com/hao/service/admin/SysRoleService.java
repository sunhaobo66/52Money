package com.hao.service.admin;

import com.hao.core.vo.DataGridResult;
import com.hao.core.vo.Query;
import com.hao.domain.admin.SysRole;

import java.util.List;

public interface SysRoleService {
    //分页业务方法
    DataGridResult getPageList(Query query);

    void deleteBatch(Long[] ids);

    SysRole getById(Long roleId);

    void save(SysRole role);

    void update(SysRole role);

    List<SysRole> findAll();

    List<String> selectRoleNameList(Long userId);
}
