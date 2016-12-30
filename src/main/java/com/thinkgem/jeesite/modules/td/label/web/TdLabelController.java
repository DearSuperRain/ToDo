/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.td.label.web;

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
import com.thinkgem.jeesite.modules.td.label.entity.TdLabel;
import com.thinkgem.jeesite.modules.td.label.service.TdLabelService;

/**
 * 标签管理Controller
 * @author DRY
 * @version 2016-12-30
 */
@Controller
@RequestMapping(value = "${adminPath}/td/tdLabel")
public class TdLabelController extends BaseController {

	@Autowired
	private TdLabelService tdLabelService;
	
	@ModelAttribute
	public TdLabel get(@RequestParam(required=false) String id) {
		TdLabel entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tdLabelService.get(id);
		}
		if (entity == null){
			entity = new TdLabel();
		}
		return entity;
	}
	
	@RequiresPermissions("td:tdLabel:view")
	@RequestMapping(value = {"list", ""})
	public String list(TdLabel tdLabel, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TdLabel> page = tdLabelService.findPage(new Page<TdLabel>(request, response), tdLabel); 
		model.addAttribute("page", page);
		return "modules/td/tdLabelList";
	}

	@RequiresPermissions("td:tdLabel:view")
	@RequestMapping(value = "form")
	public String form(TdLabel tdLabel, Model model) {
		model.addAttribute("tdLabel", tdLabel);
		return "modules/td/tdLabelForm";
	}

	@RequiresPermissions("td:tdLabel:edit")
	@RequestMapping(value = "save")
	public String save(TdLabel tdLabel, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tdLabel)){
			return form(tdLabel, model);
		}
		tdLabelService.save(tdLabel);
		addMessage(redirectAttributes, "保存标签信息成功");
		return "redirect:"+Global.getAdminPath()+"/td/tdLabel/?repage";
	}
	
	@RequiresPermissions("td:tdLabel:edit")
	@RequestMapping(value = "delete")
	public String delete(TdLabel tdLabel, RedirectAttributes redirectAttributes) {
		tdLabelService.delete(tdLabel);
		addMessage(redirectAttributes, "删除标签信息成功");
		return "redirect:"+Global.getAdminPath()+"/td/tdLabel/?repage";
	}

}