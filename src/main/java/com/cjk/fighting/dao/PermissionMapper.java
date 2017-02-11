package com.cjk.fighting.dao;

import java.util.List;

import com.cjk.fighting.model.Permission;

public interface PermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);
    
    List<Permission> selectPermissionByRoleID(Integer id);
}