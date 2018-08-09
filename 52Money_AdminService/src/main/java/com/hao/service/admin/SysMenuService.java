package com.hao.service.admin;

import com.hao.core.vo.DataGridResult;
import com.hao.core.vo.Query;
import com.hao.domain.admin.SysMenu;

import java.util.List;

public interface SysMenuService {
    //分页业务方法
    DataGridResult getPageList(Query query);

    void deleteBatch(Long[] ids);

    SysMenu getById(Long menuId);

    void save(SysMenu menu);

    void update(SysMenu menu);

    List<SysMenu> getNotButtonMenuList();

    List<SysMenu> findAll();

    List<String> getUserPermsList(Long userId);

    List<SysMenu> getTopMenuList();

    List<SysMenu> findUserMenuList(Long userId);
}
