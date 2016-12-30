/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.td.site.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.td.site.entity.TdSite;
import com.thinkgem.jeesite.modules.td.site.dao.TdSiteDao;

/**
 * 地点管理Service
 * @author DRY
 * @version 2016-12-30
 */
@Service
@Transactional(readOnly = true)
public class TdSiteService extends CrudService<TdSiteDao, TdSite> {

	public TdSite get(String id) {
		return super.get(id);
	}
	
	public List<TdSite> findList(TdSite tdSite) {
		return super.findList(tdSite);
	}
	
	public Page<TdSite> findPage(Page<TdSite> page, TdSite tdSite) {
		return super.findPage(page, tdSite);
	}
	
	@Transactional(readOnly = false)
	public void save(TdSite tdSite) {
		super.save(tdSite);
	}
	
	@Transactional(readOnly = false)
	public void delete(TdSite tdSite) {
		super.delete(tdSite);
	}
	
}