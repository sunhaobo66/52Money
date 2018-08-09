package com.hao.service.admin;


import com.hao.core.vo.DataGridResult;
import com.hao.core.vo.Query;
import com.hao.domain.admin.SysLog;

public interface SysLogService {

	//分页业务方法
	DataGridResult getPageList(Query query);

	void save(SysLog sysLog);
}
