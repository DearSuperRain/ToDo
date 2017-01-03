/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.td.task.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 任务管理Entity
 * @author DRY
 * @version 2016-12-30
 */
public class TdTask extends DataEntity<TdTask> {
	
	private static final long serialVersionUID = 1L;
	private String taskContent;		// 任务内容
	private Integer fourQuadrant;		// 四象限
	private Date beginDate;		// 任务开始日期
	private Date endDate;		// 任务结束日期
	private String locationGroup;		// 所属群组
	private String locationProject;		// 所属项目
	private String taskLabel;		// 任务标签
	private String taskSite;		// 任务地点
	private Integer taskStatus;		// 任务状态
	private Integer isRemind;		// 是否提醒
	private Integer isRepeat;		// 是否重复
	private String taskRemarks;		// 任务备注
	private String taskSummary;		// 任务总结
	
	private String groupName;	//群组名
	private String projectName;	//群组名
	private String labelName;	//群组名
	private String siteName;	//群组名
	
	public TdTask() {
		super();
	}

	public TdTask(String id){
		super(id);
	}

	@Length(min=0, max=255, message="任务内容长度必须介于 0 和 255 之间")
	public String getTaskContent() {
		return taskContent;
	}

	public void setTaskContent(String taskContent) {
		this.taskContent = taskContent;
	}
	
	public Integer getFourQuadrant() {
		return fourQuadrant;
	}

	public void setFourQuadrant(Integer fourQuadrant) {
		this.fourQuadrant = fourQuadrant;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	@Length(min=0, max=64, message="所属群组长度必须介于 0 和 64 之间")
	public String getLocationGroup() {
		return locationGroup;
	}

	public void setLocationGroup(String locationGroup) {
		this.locationGroup = locationGroup;
	}
	
	@Length(min=0, max=64, message="所属项目长度必须介于 0 和 64 之间")
	public String getLocationProject() {
		return locationProject;
	}

	public void setLocationProject(String locationProject) {
		this.locationProject = locationProject;
	}
	
	@Length(min=0, max=64, message="任务标签长度必须介于 0 和 64 之间")
	public String getTaskLabel() {
		return taskLabel;
	}

	public void setTaskLabel(String taskLabel) {
		this.taskLabel = taskLabel;
	}
	
	@Length(min=0, max=64, message="任务地点长度必须介于 0 和 64 之间")
	public String getTaskSite() {
		return taskSite;
	}

	public void setTaskSite(String taskSite) {
		this.taskSite = taskSite;
	}
	
	public Integer getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(Integer taskStatus) {
		this.taskStatus = taskStatus;
	}
	
	public Integer getIsRemind() {
		return isRemind;
	}

	public void setIsRemind(Integer isRemind) {
		this.isRemind = isRemind;
	}
	
	public Integer getIsRepeat() {
		return isRepeat;
	}

	public void setIsRepeat(Integer isRepeat) {
		this.isRepeat = isRepeat;
	}
	
	@Length(min=0, max=64, message="任务备注长度必须介于 0 和 64 之间")
	public String getTaskRemarks() {
		return taskRemarks;
	}

	public void setTaskRemarks(String taskRemarks) {
		this.taskRemarks = taskRemarks;
	}
	
	@Length(min=0, max=1024, message="任务总结长度必须介于 0 和 1024 之间")
	public String getTaskSummary() {
		return taskSummary;
	}

	public void setTaskSummary(String taskSummary) {
		this.taskSummary = taskSummary;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getLabelName() {
		return labelName;
	}

	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	
	
}