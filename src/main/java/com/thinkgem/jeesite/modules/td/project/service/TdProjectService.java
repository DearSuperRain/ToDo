/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.td.project.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.td.project.entity.TdProject;
import com.thinkgem.jeesite.modules.td.project.dao.TdProjectDao;

/**
 * 项目管理Service
 * @author DRY
 * @version 2016-12-30
 */
@Service
@Transactional(readOnly = true)
public class TdProjectService extends CrudService<TdProjectDao, TdProject> {

	public TdProject get(String id) {
		return super.get(id);
	}
	
	public List<TdProject> findList(TdProject tdProject) {
		return super.findList(tdProject);
	}
	
	public Page<TdProject> findPage(Page<TdProject> page, TdProject tdProject) {
		return super.findPage(page, tdProject);
	}
	
	@Transactional(readOnly = false)
	public void save(TdProject tdProject) {
		super.save(tdProject);
	}
	
	@Transactional(readOnly = false)
	public void delete(TdProject tdProject) {
		super.delete(tdProject);
	}
	
}