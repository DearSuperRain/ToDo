/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.td.task.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.td.task.entity.TdTask;
import com.thinkgem.jeesite.modules.td.task.dao.TdTaskDao;

/**
 * 任务管理Service
 * @author DRY
 * @version 2016-12-30
 */
@Service
@Transactional(readOnly = true)
public class TdTaskService extends CrudService<TdTaskDao, TdTask> {

	public TdTask get(String id) {
		return super.get(id);
	}
	
	public List<TdTask> findList(TdTask tdTask) {
		return super.findList(tdTask);
	}
	
	public Page<TdTask> findPage(Page<TdTask> page, TdTask tdTask) {
		return super.findPage(page, tdTask);
	}
	
	@Transactional(readOnly = false)
	public void save(TdTask tdTask) {
		super.save(tdTask);
	}
	
	@Transactional(readOnly = false)
	public void delete(TdTask tdTask) {
		super.delete(tdTask);
	}
	
}