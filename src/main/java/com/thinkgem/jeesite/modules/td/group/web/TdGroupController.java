/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.td.group.web;

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
import com.thinkgem.jeesite.modules.td.group.entity.TdGroup;
import com.thinkgem.jeesite.modules.td.group.service.TdGroupService;

/**
 * 群组管理Controller
 * @author DRY
 * @version 2016-12-30
 */
@Controller
@RequestMapping(value = "${adminPath}/td/tdGroup")
public class TdGroupController extends BaseController {

	@Autowired
	private TdGroupService tdGroupService;
	
	@ModelAttribute
	public TdGroup get(@RequestParam(required=false) String id) {
		TdGroup entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tdGroupService.get(id);
		}
		if (entity == null){
			entity = new TdGroup();
		}
		return entity;
	}
	
	@RequiresPermissions("td:tdGroup:view")
	@RequestMapping(value = {"list", ""})
	public String list(TdGroup tdGroup, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TdGroup> page = tdGroupService.findPage(new Page<TdGroup>(request, response), tdGroup); 
		model.addAttribute("page", page);
		return "modules/td/tdGroupList";
	}

	@RequiresPermissions("td:tdGroup:view")
	@RequestMapping(value = "form")
	public String form(TdGroup tdGroup, Model model) {
		model.addAttribute("tdGroup", tdGroup);
		return "modules/td/tdGroupForm";
	}
	
	@RequiresPermissions("td:tdGroup:view")
	@RequestMapping(value = "view")
	public String view(TdGroup tdGroup, Model model) {
		model.addAttribute("tdGroup", tdGroup);
		return "modules/td/tdGroupView";
	}

	@RequiresPermissions("td:tdGroup:edit")
	@RequestMapping(value = "save")
	public String save(TdGroup tdGroup, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tdGroup)){
			return form(tdGroup, model);
		}
		tdGroupService.save(tdGroup);
		addMessage(redirectAttributes, "保存群组信息成功");
		return "redirect:"+Global.getAdminPath()+"/td/tdGroup/?repage";
	}
	
	@RequiresPermissions("td:tdGroup:edit")
	@RequestMapping(value = "delete")
	public String delete(TdGroup tdGroup, RedirectAttributes redirectAttributes) {
		tdGroupService.delete(tdGroup);
		addMessage(redirectAttributes, "删除群组信息成功");
		return "redirect:"+Global.getAdminPath()+"/td/tdGroup/?repage";
	}

}