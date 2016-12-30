/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.td.label.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.td.label.entity.TdLabel;

/**
 * 标签管理DAO接口
 * @author DRY
 * @version 2016-12-30
 */
@MyBatisDao
public interface TdLabelDao extends CrudDao<TdLabel> {
	
}