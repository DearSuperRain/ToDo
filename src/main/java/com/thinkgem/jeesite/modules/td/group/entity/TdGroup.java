/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.td.group.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 群组管理Entity
 * @author DRY
 * @version 2016-12-30
 */
public class TdGroup extends DataEntity<TdGroup> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 群组名称
	private String introduction;		// 群组说明
	
	public TdGroup() {
		super();
	}

	public TdGroup(String id){
		super(id);
	}

	@Length(min=0, max=64, message="群组名称长度必须介于 0 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=64, message="群组说明长度必须介于 0 和 64 之间")
	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	
}