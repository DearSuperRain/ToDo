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

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.td.task.entity.TdTask;
import com.thinkgem.jeesite.modules.td.task.service.TdTaskService;

/**
 * 任务管理Controller
 * @author DRY
 * @version 2016-12-30
 */
@Controller
@RequestMapping(value = "${adminPath}/task/tdTask")
public class TdTaskController extends BaseController {

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
	
	@RequiresPermissions("task:tdTask:view")
	@RequestMapping(value = {"list", ""})
	public String list(TdTask tdTask, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TdTask> page = tdTaskService.findPage(new Page<TdTask>(request, response), tdTask); 
		model.addAttribute("page", page);
		return "td/task/tdTaskList";
	}

	@RequiresPermissions("task:tdTask:view")
	@RequestMapping(value = "form")
	public String form(TdTask tdTask, Model model) {
		model.addAttribute("tdTask", tdTask);
		return "td/task/tdTaskForm";
	}

	@RequiresPermissions("task:tdTask:edit")
	@RequestMapping(value = "save")
	public String save(TdTask tdTask, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tdTask)){
			return form(tdTask, model);
		}
		tdTaskService.save(tdTask);
		addMessage(redirectAttributes, "保存任务信息成功");
		return "redirect:"+Global.getAdminPath()+"/task/tdTask/?repage";
	}
	
	@RequiresPermissions("task:tdTask:edit")
	@RequestMapping(value = "delete")
	public String delete(TdTask tdTask, RedirectAttributes redirectAttributes) {
		tdTaskService.delete(tdTask);
		addMessage(redirectAttributes, "删除任务信息成功");
		return "redirect:"+Global.getAdminPath()+"/task/tdTask/?repage";
	}

}