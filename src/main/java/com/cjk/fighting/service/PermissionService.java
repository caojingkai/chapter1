package com.cjk.fighting.service;

import java.util.List;

import com.cjk.fighting.model.Permission;

public interface PermissionService {

	List<Permission> selectPermissionByRoleID(int id);
}
