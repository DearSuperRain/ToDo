/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.td.site.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 地点管理Entity
 * @author DRY
 * @version 2016-12-30
 */
public class TdSite extends DataEntity<TdSite> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 地点名称
	
	public TdSite() {
		super();
	}

	public TdSite(String id){
		super(id);
	}

	@Length(min=0, max=64, message="地点名称长度必须介于 0 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}