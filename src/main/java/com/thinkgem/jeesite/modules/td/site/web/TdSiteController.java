/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.td.site.web;

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
import com.thinkgem.jeesite.modules.td.site.entity.TdSite;
import com.thinkgem.jeesite.modules.td.site.service.TdSiteService;

/**
 * 地点管理Controller
 * @author DRY
 * @version 2016-12-30
 */
@Controller
@RequestMapping(value = "${adminPath}/site/tdSite")
public class TdSiteController extends BaseController {

	@Autowired
	private TdSiteService tdSiteService;
	
	@ModelAttribute
	public TdSite get(@RequestParam(required=false) String id) {
		TdSite entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tdSiteService.get(id);
		}
		if (entity == null){
			entity = new TdSite();
		}
		return entity;
	}
	
	@RequiresPermissions("site:tdSite:view")
	@RequestMapping(value = {"list", ""})
	public String list(TdSite tdSite, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TdSite> page = tdSiteService.findPage(new Page<TdSite>(request, response), tdSite); 
		model.addAttribute("page", page);
		return "td/site/tdSiteList";
	}

	@RequiresPermissions("site:tdSite:view")
	@RequestMapping(value = "form")
	public String form(TdSite tdSite, Model model) {
		model.addAttribute("tdSite", tdSite);
		return "td/site/tdSiteForm";
	}

	@RequiresPermissions("site:tdSite:edit")
	@RequestMapping(value = "save")
	public String save(TdSite tdSite, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tdSite)){
			return form(tdSite, model);
		}
		tdSiteService.save(tdSite);
		addMessage(redirectAttributes, "保存地点信息成功");
		return "redirect:"+Global.getAdminPath()+"/site/tdSite/?repage";
	}
	
	@RequiresPermissions("site:tdSite:edit")
	@RequestMapping(value = "delete")
	public String delete(TdSite tdSite, RedirectAttributes redirectAttributes) {
		tdSiteService.delete(tdSite);
		addMessage(redirectAttributes, "删除地点信息成功");
		return "redirect:"+Global.getAdminPath()+"/site/tdSite/?repage";
	}

}