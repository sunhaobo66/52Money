package com.hao.service.log;

import com.hao.core.vo.R;
import com.hao.domain.user.LoginLog;

import java.util.List;

public interface LoginLogService {

    //新增
    R save(LoginLog log);

    //查询
    List<LoginLog> queryAll();

}
