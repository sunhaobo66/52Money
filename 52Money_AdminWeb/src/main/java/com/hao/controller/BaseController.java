package com.hao.controller;

import com.hao.core.shiro.ShiroUtil;
import com.hao.domain.admin.SysUser;

public abstract class BaseController{
    protected SysUser getUser() {
        return (SysUser) ShiroUtil.getSession().getAttribute("sysuser");
    }
    protected long getUserId() {
        return getUser().getUserId();
    }
}
