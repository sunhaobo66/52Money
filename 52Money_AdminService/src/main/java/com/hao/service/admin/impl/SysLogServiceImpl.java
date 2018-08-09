package com.hao.service.admin.impl;


import com.hao.core.vo.DataGridResult;
import com.hao.core.vo.Query;
import com.hao.domain.admin.SysLog;
import com.hao.mapper.admin.SysLogMapper;
import com.hao.service.admin.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysLogServiceImpl implements SysLogService {

	@Autowired
	private SysLogMapper mapper;
	
	@Override
	public DataGridResult getPageList(Query query) {
		Integer offset = (Integer)query.get("offset");
		Integer limit = (Integer)query.get("limit");
		
		//调用Dao查询分页列表数据
		List<SysLog> rows = mapper.selectByPage(offset, limit);
		
		//调用Dao查询总记录数
		Long total = (Long)mapper.selectCount();
				
		//创建DataGridResult对象
		DataGridResult dataGridResult = new DataGridResult(rows, total);
		return dataGridResult;
	}
	@Override
	public void save(SysLog sysLog) {
		mapper.insert(sysLog);
	}

}
