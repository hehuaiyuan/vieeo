package com.vieeo.test.repository;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vieeo.core.domain.BizEntity;
import com.vieeo.module.repository.GenericBizRepository;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:com/vieeo/resource/test.context.xml"})
public class TestBizRepository {
	
	@Resource
	GenericBizRepository bizRepository;
	
	@Test
	public void testQuery() {
		List<BizEntity> result = bizRepository.find("select u.id as id,u.userName as name,u.age as ag from User u where u.userName=?", "admin");
		for (BizEntity bizEntity : result) {
			System.out.println(bizEntity.getIntegerValue("ag"));
		}
		//使用sql查询时，如果最外层select有使用别名，则要在这一层外面再包一个select,不然别名无法识别,且包裹的select后要加别名
		//如select * from (select t.id id,t.userName uname from user_t t) s
		//Pagination<BizEntity> result = bizRepository.findBySql(1, 10,"select * from (select t.id id,t.userName uname from user_t t where t.userName='admin') s");
	}

}
