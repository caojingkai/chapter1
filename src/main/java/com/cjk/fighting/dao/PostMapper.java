package com.cjk.fighting.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.cjk.fighting.model.Post;

public interface PostMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Post record);

    int insertSelective(Post record);

    Post selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Post record);

    int updateByPrimaryKey(Post record);
    
    List<Post> selectByUserID(int userID, String orderBy);
    
    List<Post> selectByUserIDForPage(int beginNum, int endNum);
    
    List<Post>  selectForPage(RowBounds rowBounds);
    
    List<Post> selectByUserIDPage(Map map);
    
    
}