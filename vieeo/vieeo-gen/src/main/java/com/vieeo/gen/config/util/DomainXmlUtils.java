package com.vieeo.gen.config.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.vieeo.gen.config.bean.DomainConfig;
import com.vieeo.gen.config.bean.DomainConfig.DomainAttribute;

public class DomainXmlUtils {

	@SuppressWarnings("rawtypes")
	public static DomainConfig parseDomainConfig(String filePath){
        try {
        	DomainConfig result = new DomainConfig();
    		File file = new File(filePath);
    		SAXReader saxReader = new SAXReader();
			Document document = saxReader.read(file);
			Element root = document.getRootElement();
			if(root.attribute("package") != null) result.setPackageName(root.attribute("package").getValue());
			if(root.attribute("className") != null) result.setClassName(root.attribute("className").getValue());
			if(root.attribute("tableName") != null) result.setTableName(root.attribute("tableName").getValue());

			Element key = root.element("key").element("property");

			DomainConfig.DomainAttribute priKey = createAttribute(key);
			result.setPriKey(priKey);

			Iterator iter = root.elementIterator("property");
			List<DomainAttribute> attributes = new ArrayList<DomainAttribute>();
			while(iter.hasNext()){
				Element property = (Element)iter.next();
				DomainConfig.DomainAttribute attribute = createAttribute(property);
				attributes.add(attribute);
			}
			result.setAttributes(attributes);
			return result;
		} catch (DocumentException e) {
			e.printStackTrace();
			throw new RuntimeException("初始化domain模版失败,请检查目录配置信息是否正确!");
		}

	}

	private static DomainConfig.DomainAttribute createAttribute(Element property){
		DomainConfig.DomainAttribute attribute = new DomainConfig.DomainAttribute();
		if(property.attribute("name") != null) attribute.setName(property.attribute("name").getValue());

		if(property.attribute("javaType") != null)attribute.setJavaType(property.attribute("javaType").getValue());

		if(property.attribute("hbmColumn") != null)attribute.setHbmColumn(property.attribute("hbmColumn").getValue());
		if(property.attribute("hbmType") != null)attribute.setHbmType(property.attribute("hbmType").getValue());
		if(property.attribute("hbmLength") != null)attribute.setHbmLength(property.attribute("hbmLength").getValue());
		if(property.attribute("hbmDefValue") != null)attribute.setHbmDefValue(property.attribute("hbmDefValue").getValue());
		if(property.attribute("notNull") != null)attribute.setNotNull(property.attribute("notNull").getValue());

		if(property.attribute("webText") != null)attribute.setWebText(property.attribute("webText").getValue());
		if(property.attribute("webQuery") != null)attribute.setWebQuery(property.attribute("webQuery").getValue());
		if(property.attribute("webType") != null)attribute.setWebType(property.attribute("webType").getValue());
		if(property.attribute("webColumn") != null)attribute.setWebColumn(property.attribute("webColumn").getValue());
		if(property.attribute("webModify") != null)attribute.setWebModify(property.attribute("webModify").getValue());

		return attribute;
	}

}
