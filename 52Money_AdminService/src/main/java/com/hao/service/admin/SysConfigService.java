package com.hao.service.admin;


import com.hao.core.vo.DataGridResult;
import com.hao.core.vo.Query;
import com.hao.domain.admin.SysConfig;

import java.util.List;

public interface SysConfigService {

	//分页业务方法
	DataGridResult getPageList(Query query);

	void deleteBatch(Long[] ids);

	SysConfig getById(Long id);

	void save(SysConfig sysConfig);

	void update(SysConfig sysConfig);

	List<SysConfig> findByKeyPrefix(String keyPrefix);
}
