package com.cjk.fighting.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.stereotype.Service;

import com.cjk.fighting.dao.PostMapper;
import com.cjk.fighting.model.Post;
import com.cjk.fighting.utils.Page;

@Service("PostService")
public class PostServiceImp implements PostService {

	@Resource
	private PostMapper postMapper;
	
	


	public Post selectByPrimaryKey(int id) {
		// TODO Auto-generated method stub
		return postMapper.selectByPrimaryKey(id);
	}



	public List<Post> selectByUserID(int userID, String orderBy) {
		// TODO Auto-generated method stub
		return postMapper.selectByUserID(userID, orderBy);
	}



	public List<Post> selectByUserIDForPage(int beginNum, int endNum) {
		// TODO Auto-generated method stub
		return postMapper.selectByUserIDForPage(beginNum, endNum);
	}
	
	public List<Post> selectUseRowbounds()
	{
		return null;
		//postMapper.s
	}



	public List<Post> selectForPage(RowBounds rowBounds) {
		// TODO Auto-generated method stub
		
		return postMapper.selectForPage(rowBounds);
	}



	public List<Post> selectByUserIDPage(String userId) {
		// TODO Auto-generated method stub
		Map map = new HashMap<String, Object>();
		Page page = new Page();
		page.setBeginSize(0);
		page.setEndSize(2);
		
		map.put("page", page);
		map.put("userId", userId);
		List list = postMapper.selectByUserIDPage(map);
		System.out.println("Count"+page.getCount());
		return list;
	}

}
