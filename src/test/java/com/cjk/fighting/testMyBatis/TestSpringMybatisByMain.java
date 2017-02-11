package com.cjk.fighting.testMyBatis;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cjk.fighting.dao.DEPTMapper;
import com.cjk.fighting.model.DEPT;
import com.cjk.fighting.model.Permission;
import com.cjk.fighting.model.Post;
import com.cjk.fighting.model.Role;
import com.cjk.fighting.model.Users;
import com.cjk.fighting.service.DeptService;
import com.cjk.fighting.service.PostService;
import com.cjk.fighting.service.RoleService;
import com.cjk.fighting.service.UsersService;
import com.cjk.fighting.utils.Page;
import com.cjk.fighting.utils.ReRowBounds;

import static java.lang.System.out;

public class TestSpringMybatisByMain {

	
	private static ApplicationContext act;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		before();
		//testQueryDept();
		//testSession();
		//testInsertDept();
		//testPost();
		//testUser();
		//testPostForPage();
		//pageingUse();
		//testPostForPagePlanc();
		//selectByUserIDPage();
		//selectByUserRole();
		System.out.println(System.getProperty("java.io.tmpdir"));
		selectPermissionByRoleId();
		out.println("Success");
	}
	
	public static void before() 
	{
		out.println("begin");
		act = new ClassPathXmlApplicationContext("spring-mybatis.xml");
	}
	
	public static void testSession()
	{
		SqlSessionFactoryBean sessionq= (SqlSessionFactoryBean)act.getBean("sqlSessionFactory");
		out.println("Success1");
		try {
			DEPTMapper dd =(DEPTMapper)sessionq.getObject();
			dd.selectByPrimaryKey((short)20);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void testQueryDept()
	{
		DeptService deptSer=(DeptService)act.getBean("deptService");
		DEPT dept= deptSer.selectByPrimaryKey((short)20);
		out.println(dept);
		out.println();
	}
	
	public static void testInsertDept()
	{
		DeptService deptSer = (DeptService)act.getBean("deptService");
		DEPT record = new DEPT();
		record.setDeptno((short)60);
		record.setDname("PUBLICRELATION");
		record.setLoc("NOTLOC");
		deptSer.addDept(record);
	}
	
	public static void testPost()
	{
		PostService postService = (PostService)act.getBean("PostService");
		//List<Post> listPost = postService.selectByPrimaryKey(10001);
		List<Post> listPost = postService.selectByUserID(1001,"DESC");
		out.println(listPost.size());
		for(Post post:listPost)
		{
			out.println(post+":::"+":::");
		}
	    
	}
	
	public static void testUser()
	{
		UsersService usersService = (UsersService)act.getBean("UsersService");
		//List<Post> listPost = postService.selectByPrimaryKey(10001);
		Users Users = usersService.selectByUserLoginName("cqqqjk");
		out.println(Users.getPostList().size());
	
	}
	
	public static void testPostForPage()
	{
		PostService postService = (PostService)act.getBean("PostService");
		List<Post> listPost = postService.selectByUserIDForPage(2, 3);
		out.println(listPost.size());
		for(Post post:listPost)
		{
			out.println(post+":::"+":::");
		}
	}
	
	//public static void testPagingUser

	//专测mybatis 支持插件分页
	public static void pageingUse()
	{
	 Reader reader = null;
	 String resource = "mybatis-config.xml";
	 SqlSessionFactory  ssf=null;
	 SqlSession session=null;
	 try {
		reader = Resources.getResourceAsReader(resource);
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        ssf = builder.build(reader);
        session=ssf.openSession();
        DEPTMapper mapper = session.getMapper(DEPTMapper.class);
        mapper.selectByPrimaryKey((short)120);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	
	public static void testPostForPagePlanc()
	{
		PostService postService = (PostService)act.getBean("PostService");
		//List<Post> listPost = postService.selectByPrimaryKey(10001);
		Page page = new Page();
		List<Post> listPost = null;
		for( int i = 0;i<3;i++)
		{
			out.println("i:"+i);
			page.setBeginSize(i);
			page.setEndSize(i+1);	
			RowBounds rowBounds = new ReRowBounds(i,i+1);
			listPost = postService.selectForPage(rowBounds);
			ReRowBounds reRowBounds= (ReRowBounds)rowBounds;
			
			for(Post post:listPost)
			{
				out.println(post+":::"+":::"+reRowBounds.getPage().getCount());
			}
		}
		out.println(listPost.size()+":::");
		
	    
	}
	
	
	public static void selectByUserIDPage()
	{
		PostService postService = (PostService)act.getBean("PostService");
		//List<Post> listPost = postService.selectByPrimaryKey(10001);
		List<Post> listPost = postService.selectByUserIDPage("1001");
		out.println(listPost.size());
		for(Post post:listPost)
		{
			out.println(post+":::"+":::");
		}
	    
	}
	
	//测试多对多
 public static void selectByUserRole()
 {
	 UsersService usersService = (UsersService)act.getBean("UsersService");
	 Users user = usersService.selectByUserRole(1004);
	 if (user.getRoleList()!= null)
	 {
		 for(Role role : user.getRoleList())
		 {
			 out.println(role.getId()+"::"+role.getRoleName());
		 }
	 }
 }
 
 public static void selectPermissionByRoleId()
 {
	 RoleService roleService = (RoleService)act.getBean("RoleService");
	 Role role = roleService.selectPermissionByRoleId(12);
	 if (role.getPermissionList() !=null)
	 {
		 out.println(role.getId()+"::"+role.getRoleName());
		 for(Permission permission :role.getPermissionList())
		 {
			 out.println(permission.getId()+":::"+permission.getPermissionName());
		 }
	 }
	 
	 
 }
}
