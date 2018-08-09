package com.hao.privider.loan;

import com.hao.core.util.ExcelUtils;
import com.hao.core.util.ExecuteUtils;
import com.hao.core.vo.R;
import com.hao.domain.loan.Loan;
import com.hao.mapper.loan.LoanMapper;
import com.hao.service.loan.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoanServiceImpl implements LoanService {
    @Autowired
    private LoanMapper mapper;


    @Override
    public R save(Loan loan) {
       //数据库储存的金钱为分，页面显示的为元
       loan.setMoney(loan.getMoney()*100);
       loan.setMinmoney(loan.getMinmoney()*100);
       loan.setRate(loan.getRate()/100);
       return ExecuteUtils.getR(mapper.insert(loan),"新增借款");
    }
}
