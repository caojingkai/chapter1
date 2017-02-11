package com.cjk.fighting.service;

import com.cjk.fighting.model.Users;

public interface UsersService {
	
	Users selectByPrimaryKey(int userid);
	
	Users selectByUserLoginName(String loginName);

	public int addUser(Users user);
	
	Users selectByUserRole(int userid);

}
