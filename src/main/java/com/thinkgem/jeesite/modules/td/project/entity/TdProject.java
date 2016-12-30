/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.td.project.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 项目管理Entity
 * @author DRY
 * @version 2016-12-30
 */
public class TdProject extends DataEntity<TdProject> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 项目名称
	private String isArchive;		// 是否归档
	private String introduction;		// 项目说明
	
	public TdProject() {
		super();
	}

	public TdProject(String id){
		super(id);
	}

	@Length(min=0, max=64, message="项目名称长度必须介于 0 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=11, message="是否归档长度必须介于 0 和 11 之间")
	public String getIsArchive() {
		return isArchive;
	}

	public void setIsArchive(String isArchive) {
		this.isArchive = isArchive;
	}
	
	@Length(min=0, max=64, message="项目说明长度必须介于 0 和 64 之间")
	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	
}