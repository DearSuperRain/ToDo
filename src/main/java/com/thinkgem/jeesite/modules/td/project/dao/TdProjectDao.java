/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.td.project.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.td.project.entity.TdProject;

/**
 * 项目管理DAO接口
 * @author DRY
 * @version 2016-12-30
 */
@MyBatisDao
public interface TdProjectDao extends CrudDao<TdProject> {
	
}