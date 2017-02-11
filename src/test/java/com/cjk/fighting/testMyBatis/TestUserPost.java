package com.cjk.fighting.testMyBatis;

import static java.lang.System.out;

import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import com.cjk.fighting.controller.UserController;
import com.cjk.fighting.model.Post;
import com.cjk.fighting.service.PostService;

public class TestUserPost {
	
private static final Logger LOGGER = LoggerFactory.getLogger(TestSpringMybatis.class);
	
	private static ApplicationContext act;
	
	@Before
	public void setUp()
	{
		act=new ClassPathXmlApplicationContext("spring-mybatis.xml");
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testGetUserData()
	{
		//List<Post> listPost = postService.selectByUserId(10001, "desc");
		//out.println(listPost.size());
		UserController userCon = new UserController();
		Model model = new ExtendedModelMap();
		String userMain = userCon.userlogin("cjk", "123", model);
		Assert.assertEquals("userMain", userMain);
	}

}
