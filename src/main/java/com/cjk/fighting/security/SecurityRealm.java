package com.cjk.fighting.security;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.PasswordService;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.SimpleByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cjk.fighting.model.Permission;
import com.cjk.fighting.model.Role;
import com.cjk.fighting.model.Users;
import com.cjk.fighting.service.PermissionService;
import com.cjk.fighting.service.RoleService;
import com.cjk.fighting.service.UsersService;

public class SecurityRealm extends AuthorizingRealm{

	@Resource
    private UsersService usersService;
	
	@Resource
    private PermissionService permissionService;
	
	@Resource
	private RoleService roleService;
	
	@Resource
	private PasswordService passwordService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SecurityRealm.class);
	//authorization 授权，认可；批准，委任
	//authentication 证明；鉴定；证实
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		LOGGER.error("授权");
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		String userid = String.valueOf(principals.getPrimaryPrincipal());
		Users user = usersService.selectByUserLoginName(userid);

		//获得角色
		List<Role> roleList = roleService.selectRoleByUserID(Integer.valueOf(user.getUserid()));
		if (null != roleList && roleList.size() > 0)
		{
			for (Role role:roleList)
			{
				authorizationInfo.addRole(role.getRoleName());
				List<Permission> permissionList = permissionService.selectPermissionByRoleID(role.getId());
			    for (Permission permission:permissionList)
			    {
			    	authorizationInfo.addStringPermission(permission.getPermissionName());
			    }
			}
		}
		return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authenticationtoken)
			throws AuthenticationException {
		
		String userId = String.valueOf(authenticationtoken.getPrincipal());
		Users user = usersService.selectByUserLoginName(userId);
		LOGGER.error("认证"+"::"+user.getUserSalt()+user.getLoginName());
		/*
		 * String userPass = new String((char[])authenticationtoken.getCredentials());
		 * if (null != userPass && !userPass.equals(user.getPassword()))
		{
			throw new AuthenticationException("用户名或密码错误！");
		}*/
		
		SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(userId,user.getPassword(),getName());
		return simpleAuthenticationInfo;
	}

}
