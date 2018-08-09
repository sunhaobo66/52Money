package com.hao.service.admin;

import com.hao.core.vo.DataGridResult;
import com.hao.core.vo.Query;
import com.hao.core.vo.R;

public interface AuthService {
    //跟用户实名认证相关
    //分页业务方法
    DataGridResult getPageList(Query query);
    R update(int flag,int id);
}
