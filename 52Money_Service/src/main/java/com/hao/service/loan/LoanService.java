package com.hao.service.loan;

import com.hao.core.vo.R;
import com.hao.domain.loan.Loan;
//借款
public interface LoanService {
    R save(Loan loan);
}
