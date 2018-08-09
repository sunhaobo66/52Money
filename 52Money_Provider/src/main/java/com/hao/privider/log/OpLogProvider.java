package com.hao.privider.log;

import com.hao.core.util.ExecuteUtils;
import com.hao.core.vo.R;
import com.hao.domain.user.OpLog;
import com.hao.mapper.user.OpLogMapper;
import com.hao.service.log.OpLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OpLogProvider implements OpLogService {
    @Autowired
    private OpLogMapper mapper;


    @Override
    public R save(OpLog log) {
        return ExecuteUtils.getR(mapper.insert(log),"新增日志成功");
    }

    @Override
    public List<OpLog> queryAll(int type) {
        return mapper.selectByTypey(type);
    }
}
