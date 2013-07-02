package com.vieeo.test.web;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vieeo.test.domain.Light;
import com.shinesoft.scfp.base.ccm.web.bean.cust.CustViewBean;
import com.vieeo.component.exception.ControllerException;
import com.vieeo.core.condition.Conditions;
import com.vieeo.core.condition.Expressions;
import com.vieeo.core.domain.Pagination;
import com.vieeo.module.repository.GenericRepository;

@Controller
@RequestMapping("/light")
public class LightController {

	@Resource
	private GenericRepository<Light, String> lightRepository;

	@RequestMapping("/query.do")
	public String query(CustViewBean form,Model model) {
		Conditions conditions = Conditions.create();
		conditions.order(Expressions.orderAsc("dateCreated"));

		Pagination<Light> data = lightRepository.find(conditions, form.getPage(), form.getRp());
		model.addAttribute("page", data);
		model.addAttribute("form", form);
		return "light/index.ftl";
	}

	@RequestMapping("/saveOrUpdateIndex.do")
	public String saveOrUpdateIndex(String id,Model model) {
		Light domain = StringUtils.isBlank(id) ? null : lightRepository.get(id);
		model.addAttribute("domain", domain);
		return "light/modify.ftl";
	}

	@RequestMapping(value = "/saveOrUpdate.do",method=RequestMethod.POST)
	public String saveOrUpdate(@Valid Light bean) {
		if(StringUtils.isBlank(bean.getId())) {
			lightRepository.save(bean);
		}else {
			Light domain = lightRepository.get(bean.getId());
			if(domain == null) throw new ControllerException("没有找到修改的记录,请重新操作");
			BeanUtils.copyProperties(bean, domain, Light.getIgnoreProperties());
			custRepository.update(domain);
		}
		return "forward:light/query.do";
	}

	@RequestMapping(value = "/drop.do",method={RequestMethod.POST,RequestMethod.GET})
	public String delete(String id){
		if(StringUtils.isBlank(id)) throw new ControllerException("请选择要删除的记录!");
		Light domain = lightRepository.get(id);
		if(domain == null) throw new ControllerException("未找到要删除的记录!");
		lightRepository.delete(domain);
		return "forward:light/query.do";
	}

}
