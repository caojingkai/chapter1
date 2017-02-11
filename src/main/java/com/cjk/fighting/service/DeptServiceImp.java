package com.cjk.fighting.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cjk.fighting.dao.DEPTMapper;
import com.cjk.fighting.model.DEPT;

@Service("deptService")
public class DeptServiceImp implements DeptService {

	@Resource
	private DEPTMapper deptMapper;
	
	public DEPT selectByPrimaryKey(Short deptno) {
		// TODO Auto-generated method stub
		return deptMapper.selectByPrimaryKey(deptno);
	}

	public int addDept(DEPT record) {
		// TODO Auto-generated method stub
		return deptMapper.insert(record);
	}

}
