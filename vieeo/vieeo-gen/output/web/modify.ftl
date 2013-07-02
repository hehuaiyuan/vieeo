<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http:// www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
		<#include "*/include/resources.ftl">
</head>
<body>
<div id="formwrapper">
  <form action="<@spring.url '/light/saveOrUpdate.do'/>" method="post" name="modifyForm" id="modifyForm">
    <fieldset>
	    <legend>数据操作</legend>
	    <div>
	      <label for="id">年龄</label>
	      <input type="text" name="age" id="age" value="${(domain.age)!}" />
	      <br/>
	    </div>
	    <div>
	      <label for="id">地址</label>
	      <input type="text" name="address" id="address" value="${(domain.address)!}" />
	      <br/>
	    </div>
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