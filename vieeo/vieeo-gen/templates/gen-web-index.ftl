<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http:// www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
	<#noparse>
	<#include "*/include/resources.ftl">
	</#noparse>
</head>
<body>
	<#noparse>
	<form id="queryForm" name="queryForm" action="<@spring.url </#noparse>'/${(web.view)!}/query.do'<#noparse>/>" method="post">
	</#noparse>
<#if domain.attributes??>
	<#list domain.attributes as attr>
		<#if attr.webQuery?? && attr.webQuery=='true'>
		<label for="${(attr.name)!}">${(attr.webText)!}:</label><input type="text" name="${(attr.name)!}" id="${(attr.name)!}" value="<#noparse>${(form.</#noparse>${(attr.name)!}<#noparse>)!}"/></#noparse>
		</#if>
	</#list>
</#if>

	<#noparse>
		<input name="qryBtn" type="submit" class="buttom" value="查询" />
		<input name="qryBtn" type="button" class="buttom" value="新增客户" onclick="formSubmitWithUrl('#queryForm','<@spring.url '</#noparse>/${(web.view)!}/saveOrUpdateIndex.do'<#noparse>/>')"/></#noparse>
		<br/>
		<br/>
		<br/>
		<table width="90%" id="mytab" border="1" class="t1">
			<thead>
<#if domain.attributes??>
	<#list domain.attributes as attr>
		<#if attr.webColumn?? && attr.webColumn=='true'>
				<th width="10%">${(attr.webText)!}</th>
		</#if>
	</#list>
				<th width="10%">操作</th>
</#if>
			</thead>
	<#noparse>
			<#list page.data as domain>
				<tr
				<#if domain_index%2 == 0>
					class="a1"
				</#if>
				>
	</#noparse>
<#if domain.attributes??>
	<#list domain.attributes as attr>
		<#if attr.webColumn?? && attr.webColumn=='true'>
					<td><#noparse>${</#noparse>domain.${(attr.name)!}<#noparse>! }</#noparse></td>
					<td>
						<a href="<#noparse><@spring.url '</#noparse>/${(web.view)!}<#noparse>/saveOrUpdateIndex.do?id=${domain.id!}'/>">修改</a></#noparse>
						|
						<a href="<#noparse><@spring.url '</#noparse>/${(web.view)!}<#noparse>/drop.do?id=${domain.id!}'/>">删除</a></#noparse>
					</td>
		</#if>
	</#list>
</#if>
	<#noparse>
				</tr>
			</#list>
		</table>
		<#include "*/include/page.ftl">
	</form>
	</#noparse>
</body>
</html>