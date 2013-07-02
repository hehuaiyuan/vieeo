<#assign lowClassName = "${(data.className?uncap_first)!}">
<#assign className = "${(data.className)!}">
<#assign repository = "${(lowClassName)!}Repository">
package ${(config.rootPackage)!}.${(data.packageName)!};

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ${(config.rootPackage)!}.${(data.domainPackage)!}.${(className)!};
import com.shinesoft.scfp.base.ccm.web.bean.cust.CustViewBean;
import com.vieeo.component.exception.ControllerException;
import com.vieeo.core.condition.Conditions;
import com.vieeo.core.condition.Expressions;
import com.vieeo.core.domain.Pagination;
import com.vieeo.module.repository.GenericRepository;

@Controller
@RequestMapping("/${(data.view)!}")
public class ${(className)!}Controller {

	@Resource
	private GenericRepository<${(className)!}, String> ${(repository)!};

	@RequestMapping("/query.do")
	public String query(CustViewBean form,Model model) {
		Conditions conditions = Conditions.create();
		conditions.order(Expressions.orderAsc("dateCreated"));

		Pagination<${(className)!}> data = ${(repository)!}.find(conditions, form.getPage(), form.getRp());
		model.addAttribute("page", data);
		model.addAttribute("form", form);
		return "${(data.view)!}/index.ftl";
	}

	@RequestMapping("/saveOrUpdateIndex.do")
	public String saveOrUpdateIndex(String id,Model model) {
		${(className)!} domain = StringUtils.isBlank(id) ? null : ${(repository)!}.get(id);
		model.addAttribute("domain", domain);
		return "${(data.view)!}/modify.ftl";
	}

	@RequestMapping(value = "/saveOrUpdate.do",method=RequestMethod.POST)
	public String saveOrUpdate(@Valid ${(className)!} bean) {
		if(StringUtils.isBlank(bean.getId())) {
			${(repository)!}.save(bean);
		}else {
			${(className)!} domain = ${(repository)!}.get(bean.getId());
			if(domain == null) throw new ControllerException("没有找到修改的记录,请重新操作");
			BeanUtils.copyProperties(bean, domain, ${(className)!}.getIgnoreProperties());
			custRepository.update(domain);
		}
		return "forward:${(data.view)!}/query.do";
	}

	@RequestMapping(value = "/drop.do",method={RequestMethod.POST,RequestMethod.GET})
	public String delete(String id){
		if(StringUtils.isBlank(id)) throw new ControllerException("请选择要删除的记录!");
		${(className)!} domain = ${(repository)!}.get(id);
		if(domain == null) throw new ControllerException("未找到要删除的记录!");
		${(repository)!}.delete(domain);
		return "forward:${(data.view)!}/query.do";
	}

}
