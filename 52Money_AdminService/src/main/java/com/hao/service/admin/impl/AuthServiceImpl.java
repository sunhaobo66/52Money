package com.hao.service.admin.impl;

import com.hao.core.util.ExecuteUtils;
import com.hao.core.vo.DataGridResult;
import com.hao.core.vo.Query;
import com.hao.core.vo.R;
import com.hao.domain.user.UserDetail;
import com.hao.mapper.user.UserDatailMapper;
import com.hao.service.admin.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserDatailMapper mapper;

    @Override
    public DataGridResult getPageList(Query query) {
        Integer offset = (Integer)query.get("offset");
        Integer limit = (Integer)query.get("limit");
        //调用Dao查询分页列表数据
        List<UserDetail> rows = mapper.queryByPage(offset, limit);
        //调用Dao查询总记录数
        Long total = (Long)mapper.queryCount();
        //创建DataGridResult对象
        DataGridResult dataGridResult = new DataGridResult(rows, total);
        return dataGridResult;
    }

    @Override
    public R update(int flag, int id) {
        return ExecuteUtils.getR(mapper.updateId(flag,id),"实名认证审核");
    }
}
