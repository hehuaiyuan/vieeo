package com.vieeo.gen.service.support;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import com.vieeo.gen.config.TemplateConfig;
import com.vieeo.gen.service.TemplateService;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

public class FreemarkerTemplateService implements TemplateService{

	private TemplateConfig config;

	private Configuration cfg;

	public void init(){
		try {
			String projectPath = config.getRootPath();
			String path = config.getTemplatePath();
			cfg = new Configuration();
			File file = new File(projectPath+"/"+path);
			cfg.setDirectoryForTemplateLoading(file);
			cfg.setObjectWrapper(new DefaultObjectWrapper());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("初始化模版失败,请检查目录配置信息是否正确!");
		}
	}

	@Override
	public void process(Map<String,Object> params,String templateFile,String outputFileName) {
		try {
			Template template = cfg.getTemplate(templateFile, config.getEncoding());
			if(template == null) throw new RuntimeException("未找到对应的模版,无法处理输出!");
			File file = new File(config.getRootPath()+"/"+config.getOutputPath()+"/"+outputFileName);
			System.out.println("生成文件:"+file.getPath());
			if(!file.exists()) {
				file.getParentFile().mkdirs();
				file.createNewFile();
			}else {
				file.delete();
			}
			params.put("config", config);
			OutputStream stream = new FileOutputStream(file);
			Writer out = new OutputStreamWriter(stream);
			template.process(params, out);
			out.flush();
			out.close();
			stream.close();
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(outputFileName+"输出处理失败:"+e.getMessage());
		}
	}

	public TemplateConfig getConfig() {
		return config;
	}

	public void setConfig(TemplateConfig config) {
		this.config = config;
	}

	public Configuration getCfg() {
		return cfg;
	}

	public void setCfg(Configuration cfg) {
		this.cfg = cfg;
	}

}
