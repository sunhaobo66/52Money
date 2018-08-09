package com.hao.privider.log;

import com.hao.core.util.ExecuteUtils;
import com.hao.core.vo.R;
import com.hao.domain.user.LoginLog;

import com.hao.mapper.user.LoginLogMapper;
import com.hao.service.log.LoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class LoginLogProvider implements LoginLogService {
    @Autowired
    private LoginLogMapper mapper;

    @Override
    public R save(LoginLog log) {
       return ExecuteUtils.getR(mapper.insert(log),"新增日志成功");
    }

    @Override
    public List<LoginLog> queryAll() {
        return mapper.selectAll();
    }
}
