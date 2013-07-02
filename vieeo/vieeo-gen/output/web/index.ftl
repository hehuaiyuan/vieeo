<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http:// www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
		<#include "*/include/resources.ftl">
</head>
<body>
	<form id="queryForm" name="queryForm" action="<@spring.url '/light/query.do'/>" method="post">
		<label for="age">年龄:</label><input type="text" name="age" id="age" value="${(form.age)!}"/>

		<input name="qryBtn" type="submit" class="buttom" value="查询" />
		<input name="qryBtn" type="button" class="buttom" value="新增客户" onclick="formSubmitWithUrl('#queryForm','<@spring.url '/light/saveOrUpdateIndex.do'/>')"/>
		<br/>
		<br/>
		<br/>
		<table width="90%" id="mytab" border="1" class="t1">
			<thead>
				<th width="10%">年龄</th>
				<th width="10%">操作</th>
			</thead>
			<#list page.data as domain>
				<tr
				<#if domain_index%2 == 0>
					class="a1"
				</#if>
				>
					<td>${domain.age! }</td>
					<td>
						<a href="<@spring.url '/light/saveOrUpdateIndex.do?id=${domain.id!}'/>">修改</a>
						|
						<a href="<@spring.url '/light/drop.do?id=${domain.id!}'/>">删除</a>
					</td>
				</tr>
			</#list>
		</table>
		<#include "*/include/page.ftl">
	</form>
</body>
</html>