package com.cjk.fighting.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cjk.fighting.dao.RoleMapper;
import com.cjk.fighting.model.Role;

@Service("RoleService")
public class RoleServiceImpl implements RoleService {

	@Resource
	private RoleMapper roleMapper;
	
	public Role selectPermissionByRoleId(Integer id) {
		// TODO Auto-generated method stub
		return roleMapper.selectPermissionByRoleId(id);
	}

	public List<Role> selectRoleByUserID(Integer id) {
		// TODO Auto-generated method stub
		return roleMapper.selectRoleByUserID(id);
	}

}
