package com.cjk.fighting.dao;

import com.cjk.fighting.model.Salgrade;

public interface SalgradeMapper {
    int insert(Salgrade record);

    int insertSelective(Salgrade record);
}