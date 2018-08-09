package com.hao.mapper.loan;

import com.hao.domain.loan.Account;
//账户表
public interface AccountMapper {
    // 根据Id 进行删除
    int deleteByPrimaryKey (Integer id);
    //新增
    int insert(Account record);

    int insertSelective(Account record);

    //根据账户表ID 查询账户表
    Account selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Account record);

    //修改 根据 Id
    int updateByPrimaryKey(Account record);
}
