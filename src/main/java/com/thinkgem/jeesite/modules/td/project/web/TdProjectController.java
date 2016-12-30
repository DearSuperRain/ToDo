/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.td.project.web;

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
import com.thinkgem.jeesite.modules.td.project.entity.TdProject;
import com.thinkgem.jeesite.modules.td.project.service.TdProjectService;

/**
 * 项目管理Controller
 * @author DRY
 * @version 2016-12-30
 */
@Controller
@RequestMapping(value = "${adminPath}/project/tdProject")
public class TdProjectController extends BaseController {

	@Autowired
	private TdProjectService tdProjectService;
	
	@ModelAttribute
	public TdProject get(@RequestParam(required=false) String id) {
		TdProject entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tdProjectService.get(id);
		}
		if (entity == null){
			entity = new TdProject();
		}
		return entity;
	}
	
	@RequiresPermissions("project:tdProject:view")
	@RequestMapping(value = {"list", ""})
	public String list(TdProject tdProject, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TdProject> page = tdProjectService.findPage(new Page<TdProject>(request, response), tdProject); 
		model.addAttribute("page", page);
		return "td/project/tdProjectList";
	}

	@RequiresPermissions("project:tdProject:view")
	@RequestMapping(value = "form")
	public String form(TdProject tdProject, Model model) {
		model.addAttribute("tdProject", tdProject);
		return "td/project/tdProjectForm";
	}

	@RequiresPermissions("project:tdProject:edit")
	@RequestMapping(value = "save")
	public String save(TdProject tdProject, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tdProject)){
			return form(tdProject, model);
		}
		tdProjectService.save(tdProject);
		addMessage(redirectAttributes, "保存项目信息成功");
		return "redirect:"+Global.getAdminPath()+"/project/tdProject/?repage";
	}
	
	@RequiresPermissions("project:tdProject:edit")
	@RequestMapping(value = "delete")
	public String delete(TdProject tdProject, RedirectAttributes redirectAttributes) {
		tdProjectService.delete(tdProject);
		addMessage(redirectAttributes, "删除项目信息成功");
		return "redirect:"+Global.getAdminPath()+"/project/tdProject/?repage";
	}

}