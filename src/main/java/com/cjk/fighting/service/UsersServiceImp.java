package com.cjk.fighting.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.cjk.fighting.dao.UsersMapper;
import com.cjk.fighting.model.Users;

@Service("UsersService")
public class UsersServiceImp implements UsersService {

	@Resource
	private UsersMapper usersMapper;
	
	@Resource
	private TransactionTemplate transactionTemplate;
	
	public Users selectByPrimaryKey(int userid) 
	{
		// TODO Auto-generated method stub
		return usersMapper.selectByPrimaryKey(userid);
	}

	public Users selectByUserLoginName(String loginName) {
		// TODO Auto-generated method stub
		return usersMapper.selectByUserLoginName(loginName);
	}
	
	/**
	 * @Transactional(propagation=Propagation.REQUIRED)
	 */
	
	public int addUser(final Users user)
	{
		transactionTemplate.execute(new TransactionCallback<Void>(){
			public Void doInTransaction(TransactionStatus transactionstatus) {
				// TODO Auto-generated method stub
				usersMapper.insertSelective(user);
				 return null;
			}
		});
		return 1;
		//return usersMapper.insertSelective(user);
	}

	public Users selectByUserRole(int userid) {
		// TODO Auto-generated method stub
		
		return usersMapper.selectByUserRole(userid);
	}

}
