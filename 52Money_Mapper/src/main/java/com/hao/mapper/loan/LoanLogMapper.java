package com.hao.mapper.loan;

import com.hao.domain.loan.LoanLog;

//借款记录
public interface LoanLogMapper {


    int deleteByPrimaryKey(Integer id);

    int insert(LoanLog record);

    int insertSelective(LoanLog record);

    LoanLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LoanLog record);

    int updateByPrimaryKey(LoanLog record);
}
