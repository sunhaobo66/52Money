package com.hao.service.admin;

import com.hao.core.vo.DataGridResult;
import com.hao.core.vo.Query;
import com.hao.domain.admin.SysUser;


public interface SysUserService {
    //分页业务方法
    DataGridResult getPageList(Query query);

    void deleteBatch(Long[] ids);

    SysUser getById(Long userId);

    void save(SysUser user);

    void update(SysUser user);

    SysUser getByUsername(String username);
}
