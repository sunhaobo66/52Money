package com.hao.privider.loan;

import com.hao.core.util.ExecuteUtils;
import com.hao.core.vo.R;
import com.hao.domain.loan.LoanLog;
import com.hao.mapper.loan.LoanLogMapper;
import com.hao.service.loan.LoanLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoanLogServiceImpl implements LoanLogService {
    @Autowired
    private LoanLogMapper mapper;


    @Override
    public R save(LoanLog loanLog) {
        return ExecuteUtils.getR(mapper.insert(loanLog),"新增借款日志");
    }
}
