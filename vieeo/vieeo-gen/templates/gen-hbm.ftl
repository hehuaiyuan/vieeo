<?xml version="1.0" encoding='UTF-8'?>
<!DOCTYPE hibernate-mapping PUBLIC "-//Hibernate/Hibernate Mapping DTD 3.0//EN"
	"http://hibernate.sourceforge.net/hibernate-mapping-3.0.dtd">
<hibernate-mapping package="${domain.packageName}" default-lazy="true">

	<class name="${domain.className}" table="${domain.tableName}">
		<id name="${(domain.priKey.name)!}" column="${(domain.priKey.hbmColumn)!}" unsaved-value="null" type="${(domain.priKey.hbmType)!}" length="${(domain.priKey.hbmLength)!}">
			<generator class="uuid" />
		</id>
		<property name="userCreated" length="100" update="false" />
		<property name="userLastModified" length="100" insert="false" />
		<property name="dateCreated" type="date" update="false" />
		<property name="dateLastModified" type="date" insert="false" />

		<#if domain.attributes??>
			<#list domain.attributes as attr>
		<property name="${(attr.name)!}" column="${(attr.hbmColumn)!}" type="${(attr.hbmType)!}" <#if attr.hbmLength??>length="${(attr.hbmLength)!}"</#if> <#if attr.notNull??>not-null="${(attr.notNull)!}"</#if>/>
			</#list>
		</#if>

	</class>

</hibernate-mapping>