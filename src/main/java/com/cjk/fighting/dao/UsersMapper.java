package com.cjk.fighting.dao;

import com.cjk.fighting.model.Users;
import java.math.BigDecimal;

public interface UsersMapper {
    int deleteByPrimaryKey(int userid);

    int insert(Users record);

    int insertSelective(Users record);

    Users selectByPrimaryKey(int userid);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);
    
    Users selectByUserLoginName(String userPhone);
    
    Users selectByUserRole(int userid);
    
    Users selectByIdPass(int userid, String password);

}