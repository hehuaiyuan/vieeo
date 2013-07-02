package com.vieeo.gen.setup;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.vieeo.gen.build.Builder;

public class GenSetup {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:fundchannel/gen-context.xml");
		Builder builder = (Builder)context.getBean("genBuilder");
		builder.build();
	}
}
