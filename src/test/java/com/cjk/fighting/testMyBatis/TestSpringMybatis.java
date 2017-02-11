package com.cjk.fighting.testMyBatis;


import static java.lang.System.out;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cjk.fighting.model.DEPT;
import com.cjk.fighting.service.DeptService;


public class TestSpringMybatis {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TestSpringMybatis.class);
	
	private static ApplicationContext act;
	
	@Before
	public void setUp()
	{
		act=new ClassPathXmlApplicationContext("spring-mybatis.xml");
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testGetData()
	{
		DeptService deptSer=(DeptService)act.getBean("deptService");
		DEPT dept= deptSer.selectByPrimaryKey((short)20);
		out.println(dept);
		Assert.assertNotNull(dept);
	}

}
