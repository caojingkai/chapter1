package com.cjk.fighting.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cjk.fighting.dao.PermissionMapper;
import com.cjk.fighting.model.Permission;

@Service("PermissionService")
public class PermissionServiceImpl implements PermissionService {

	@Resource
	private PermissionMapper permissionMapper;
	
	public List<Permission> selectPermissionByRoleID(int id) {
		// TODO Auto-generated method stub
		return permissionMapper.selectPermissionByRoleID(id);
	}

}
