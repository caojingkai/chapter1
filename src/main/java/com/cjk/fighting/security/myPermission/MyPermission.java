package com.cjk.fighting.security.myPermission;

import java.io.Serializable;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.PermissionResolver;


public class MyPermission implements Serializable, Permission {

	public boolean implies(Permission permission) {
		// TODO Auto-generated method stub
		return false;
	}



}
