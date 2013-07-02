<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http:// www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
	<#noparse>
	<#include "*/include/resources.ftl">
	</#noparse>
</head>
<body>
<div id="formwrapper">
  <form action="<#noparse><@spring.url '</#noparse>/${(web.view)!}<#noparse>/saveOrUpdate.do'/>" method="post" name="modifyForm" id="modifyForm"></#noparse>
    <fieldset>
	    <legend>数据操作</legend>
<#if domain.attributes??>
	<#list domain.attributes as attr>
	 <#if attr.webModify?? && attr.webModify=='true'>
	    <div>
	      <label for="id">${(attr.webText)!}</label>
	      <input type="text" name="${(attr.name)!}" id="${(attr.name)!}" value="<#noparse>${(</#noparse>domain.${(attr.name)!}<#noparse>)!}" /></#noparse>
	      <br/>
	    </div>
	 </#if>
	</#list>
</#if>
	    <div class="cookiechk">
	    	<label></label>
	      <input name="sub" type="submit" class="buttom" value="提交" />
	      <input name="sub" type="button" class="buttom" onclick="goBack();" value="返回" />
	    </div>
    </fieldset>
  </form>
  <br/>
</div>

</body>
</html>