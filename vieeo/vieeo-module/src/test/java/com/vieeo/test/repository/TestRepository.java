package com.vieeo.test.repository;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vieeo.core.condition.Conditions;
import com.vieeo.core.condition.Expressions;
import com.vieeo.domain.User;
import com.vieeo.module.repository.GenericRepository;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:com/vieeo/resource/test.context.xml"})
public class TestRepository {

	@Resource
	private GenericRepository<User, Long> userRepository;


	public void testAdd(){
		User user = new User();
		user.setUserName("testname");
		userRepository.save(user);
	}


	public void testGet() {
		User user = userRepository.get(Conditions.create().and(Expressions.eq("userName", "testname")));
		System.out.println(user == null ? " is null": user.getId());
	}


	public void testUpdate() {
		User user = userRepository.get(Conditions.create().and(Expressions.eq("userName", "testname")));
		user.setUserName("testname1");
		userRepository.update(user);
		System.out.println(user == null ? " is null": user.getId());
	}

	@Test
	public void testDel() {
		User user = userRepository.get(Conditions.create().and(Expressions.eq("userName", "testname")));
		userRepository.deleteByIdsWithForeign(user.getId());
		System.out.println(user == null ? " is null": user.getId());
	}

}
