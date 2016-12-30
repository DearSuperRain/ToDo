/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.td.group.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.td.group.entity.TdGroup;

/**
 * 群组管理DAO接口
 * @author DRY
 * @version 2016-12-30
 */
@MyBatisDao
public interface TdGroupDao extends CrudDao<TdGroup> {
	
}