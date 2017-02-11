package com.cjk.fighting.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.cjk.fighting.model.Post;
import com.cjk.fighting.utils.Page;

public interface PostService {
	
	List<Post> selectByUserIDForPage(int beginNum, int endNum);

	List<Post> selectByUserID(int userID, String orderBy);
	
	Post selectByPrimaryKey(int id);
	
	List<Post> selectForPage(RowBounds rowBounds);
	
	List<Post> selectByUserIDPage(String userId);
}
