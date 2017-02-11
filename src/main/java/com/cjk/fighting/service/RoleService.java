package com.cjk.fighting.service;

import java.util.List;

import com.cjk.fighting.model.Role;

public interface RoleService {
  
	Role selectPermissionByRoleId(Integer id);
	
	List<Role> selectRoleByUserID(Integer id);
}
