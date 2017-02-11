package com.cjk.fighting.service;

import com.cjk.fighting.model.DEPT;

public interface DeptService {

	DEPT selectByPrimaryKey(Short deptno);
	
	int addDept(DEPT record);
}
