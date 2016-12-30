/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.td.group.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.td.group.entity.TdGroup;
import com.thinkgem.jeesite.modules.td.group.dao.TdGroupDao;

/**
 * 群组管理Service
 * @author DRY
 * @version 2016-12-30
 */
@Service
@Transactional(readOnly = true)
public class TdGroupService extends CrudService<TdGroupDao, TdGroup> {

	public TdGroup get(String id) {
		return super.get(id);
	}
	
	public List<TdGroup> findList(TdGroup tdGroup) {
		return super.findList(tdGroup);
	}
	
	public Page<TdGroup> findPage(Page<TdGroup> page, TdGroup tdGroup) {
		return super.findPage(page, tdGroup);
	}
	
	@Transactional(readOnly = false)
	public void save(TdGroup tdGroup) {
		super.save(tdGroup);
	}
	
	@Transactional(readOnly = false)
	public void delete(TdGroup tdGroup) {
		super.delete(tdGroup);
	}
	
}