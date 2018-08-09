package com.hao.service.log;

import com.hao.core.vo.R;
import com.hao.domain.user.OpLog;

import java.util.List;

public interface OpLogService {
    //新增
    R save(OpLog log);

    //查询
    List<OpLog> queryAll(int type);
}
