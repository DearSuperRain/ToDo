/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.td.task.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sun.tools.javac.util.List;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.td.group.entity.TdGroup;
import com.thinkgem.jeesite.modules.td.group.service.TdGroupService;
import com.thinkgem.jeesite.modules.td.label.entity.TdLabel;
import com.thinkgem.jeesite.modules.td.label.service.TdLabelService;
import com.thinkgem.jeesite.modules.td.project.entity.TdProject;
import com.thinkgem.jeesite.modules.td.project.service.TdProjectService;
import com.thinkgem.jeesite.modules.td.site.entity.TdSite;
import com.thinkgem.jeesite.modules.td.site.service.TdSiteService;
import com.thinkgem.jeesite.modules.td.task.entity.TdTask;
import com.thinkgem.jeesite.modules.td.task.service.TdTaskService;

/**
 * 任务管理Controller
 * @author DRY
 * @version 2016-12-30
 */
@Controller
@RequestMapping(value = "${adminPath}/td/tdTask")
public class TdTaskController extends BaseController {
	@Autowired
	private TdGroupService tdGroupService;
	
	@Autowired
	private TdProjectService tdProjectService;
	
	@Autowired
	private TdLabelService tdLabelService;
	
	@Autowired
	private TdSiteService tdSiteService;
	
	@Autowired
	private TdTaskService tdTaskService;
	
	@ModelAttribute
	public TdTask get(@RequestParam(required=false) String id) {
		TdTask entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tdTaskService.get(id);
		}
		if (entity == null){
			entity = new TdTask();
		}
		return entity;
	}
	
	@RequiresPermissions("td:tdTask:view")
	@RequestMapping(value = {"list", ""})
	public String list(TdTask tdTask, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TdTask> page = tdTaskService.findPage(new Page<TdTask>(request, response), tdTask); 
		model.addAttribute("page", page);
		return "modules/td/tdTaskList";
	}

	@RequiresPermissions("td:tdTask:view")
	@RequestMapping(value = "form")
	public String form(TdTask tdTask, Model model) {
		//获取群组列表信息
		TdGroup tdGroup = new TdGroup();
		java.util.List<TdGroup> groups = tdGroupService.findList(tdGroup);
		model.addAttribute("groups", groups);
		
		//获取项目列表信息
		TdProject tdProject = new TdProject();
		java.util.List<TdProject> projects = tdProjectService.findList(tdProject);
		model.addAttribute("projects", projects);
		
		//获取标签信息列表
		TdLabel tdLabel = new TdLabel();
		java.util.List<TdLabel> labels = tdLabelService.findList(tdLabel);
		model.addAttribute("labels", labels);
		
		//获取地点信息列表
		TdSite tdSite = new TdSite();
		java.util.List<TdSite> sites = tdSiteService.findList(tdSite);
		model.addAttribute("sites",sites);
		
		model.addAttribute("tdTask", tdTask);
		return "modules/td/tdTaskForm";
	}
	
	@RequiresPermissions("td:tdTask:view")
	@RequestMapping(value = "view")
	public String view(TdTask tdTask, Model model) {
		/*//获取群组列表信息
		TdGroup tdGroup = new TdGroup();
		java.util.List<TdGroup> groups = tdGroupService.findList(tdGroup);
		model.addAttribute("groups", groups);
		
		//获取项目列表信息
		TdProject tdProject = new TdProject();
		java.util.List<TdProject> projects = tdProjectService.findList(tdProject);
		model.addAttribute("projects", projects);
		
		//获取标签信息列表
		TdLabel tdLabel = new TdLabel();
		java.util.List<TdLabel> labels = tdLabelService.findList(tdLabel);
		model.addAttribute("labels", labels);
		
		//获取地点信息列表
		TdSite tdSite = new TdSite();
		java.util.List<TdSite> sites = tdSiteService.findList(tdSite);
		model.addAttribute("sites",sites);
		*/
		model.addAttribute("tdTask", tdTask);
		return "modules/td/tdTaskView";
	}

	@RequiresPermissions("td:tdTask:edit")
	@RequestMapping(value = "save")
	public String save(TdTask tdTask, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tdTask)){
			return form(tdTask, model);
		}
		tdTaskService.save(tdTask);
		addMessage(redirectAttributes, "保存任务信息成功");
		return "redirect:"+Global.getAdminPath()+"/td/tdTask/?repage";
	}
	
	@RequiresPermissions("td:tdTask:edit")
	@RequestMapping(value = "delete")
	public String delete(TdTask tdTask, RedirectAttributes redirectAttributes) {
		tdTaskService.delete(tdTask);
		addMessage(redirectAttributes, "删除任务信息成功");
		return "redirect:"+Global.getAdminPath()+"/td/tdTask/?repage";
	}

}