package com.hao.service.loan;

import com.hao.core.vo.R;
import com.hao.domain.loan.LoanLog;
// 借款记录
public interface LoanLogService {

    R save(LoanLog loanLog);
}
