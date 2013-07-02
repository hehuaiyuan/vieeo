package com.vieeo.test.db;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vieeo.core.condition.Conditions;
import com.vieeo.core.condition.Expressions;
import com.vieeo.core.domain.Pagination;
import com.vieeo.domain.User;
import com.vieeo.module.dao.EntityDaoException;
import com.vieeo.module.dao.GenericDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:com/vieeo/resource/db.context.xml",
								"classpath:com/vieeo/resource/property.context.xml"})
public class TestOrm {

	@Resource
	private GenericDao entityDao;

	public void testUpdate() throws EntityDaoException {
		User user = entityDao.get(User.class,"8a48eb3a2654dcea012654dced740007");
		user.setPassWord("123");
		entityDao.update(user);
	}

	public void testSave() throws EntityDaoException{
		for(int i=0;i<10;i++) {
			User user  = new User();
			user.setUserName("shaniu"+i);
			user.setAge(23+i);
			entityDao.save(user);
		}
	}

	public void testFindAll() throws EntityDaoException{
		List<User> users = entityDao.find(User.class);
		for (User user : users) {
			System.out.println(user.getUserName());
		}
	}

	public void testFind() throws EntityDaoException{
		Conditions conditions = Conditions.create().or(Expressions.eq("userName", "shaniu"),Expressions.in("age", 23,24,25,26));
		List<User> users = entityDao.find(User.class,conditions);
		for (User user : users) {
			System.out.println(user.getUserName());
		}
	}

	@Test
	public void testFindPage() throws EntityDaoException{
		Conditions conditions = Conditions.create().or(Expressions.eq("userName", "shaniu"),Expressions.in("age", 23,24,25,26));
		Pagination<User> result = entityDao.find(User.class,conditions,1,2);
		System.out.println(result.getTotalCount());
		System.out.println(result.getPageCount());
		System.out.println(result.getPageNum());
		System.out.println(result.getPageSize());
		List<User> users = result.getData();
		for (User user : users) {
			System.out.println(user.getUserName());
		}
	}
}
