package ${domain.packageName};

import com.vieeo.core.domain.BaseEntity;

/**
 *
 * @author roy.he
 *
 */
@SuppressWarnings("unchecked")
public class ${domain.className} extends BaseEntity{

	private static String[] ignorePropertiesInUpdate = new String[]{"id",
		"dateCreated","dateLastModified","userCreated","userLastModified"};

<#if domain.attributes??>
	<#list domain.attributes as attr>
	private ${attr.javaType!} ${attr.name!};

	</#list>
</#if>

	public static String[] getIgnoreProperties(){
		return ignorePropertiesInUpdate;
	}

<#if domain.attributes??>
	<#list domain.attributes as attr>
	public ${attr.javaType!} get${(attr.name?cap_first)!}(){
		return this.${(attr.name)!};
	}

	public void set${(attr.name?cap_first)!}(${attr.javaType!} ${(attr.name)!}){
		this.${(attr.name)!} = ${(attr.name)!};
	}
	</#list>
</#if>

}
