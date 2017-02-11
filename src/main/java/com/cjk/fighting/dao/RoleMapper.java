package com.cjk.fighting.dao;

import java.util.List;

import com.cjk.fighting.model.Role;

public interface RoleMapper {
	
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
    
    Role selectPermissionByRoleId(Integer id);
    
    List<Role> selectRoleByUserID(Integer id);
}