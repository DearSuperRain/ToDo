/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.td.label.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 标签管理Entity
 * @author DRY
 * @version 2016-12-30
 */
public class TdLabel extends DataEntity<TdLabel> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 标签名称
	
	public TdLabel() {
		super();
	}

	public TdLabel(String id){
		super(id);
	}

	@Length(min=0, max=64, message="标签名称长度必须介于 0 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}