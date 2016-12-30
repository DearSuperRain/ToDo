/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.td.label.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.td.label.entity.TdLabel;
import com.thinkgem.jeesite.modules.td.label.dao.TdLabelDao;

/**
 * 标签管理Service
 * @author DRY
 * @version 2016-12-30
 */
@Service
@Transactional(readOnly = true)
public class TdLabelService extends CrudService<TdLabelDao, TdLabel> {

	public TdLabel get(String id) {
		return super.get(id);
	}
	
	public List<TdLabel> findList(TdLabel tdLabel) {
		return super.findList(tdLabel);
	}
	
	public Page<TdLabel> findPage(Page<TdLabel> page, TdLabel tdLabel) {
		return super.findPage(page, tdLabel);
	}
	
	@Transactional(readOnly = false)
	public void save(TdLabel tdLabel) {
		super.save(tdLabel);
	}
	
	@Transactional(readOnly = false)
	public void delete(TdLabel tdLabel) {
		super.delete(tdLabel);
	}
	
}