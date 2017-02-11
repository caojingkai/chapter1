package ctas.test.mapper;

import ctas.test.entity.emp;

public interface empMapper {
    int deleteByPrimaryKey(Short empno);

    int insert(emp record);

    int insertSelective(emp record);

    emp selectByPrimaryKey(Short empno);

    int updateByPrimaryKeySelective(emp record);

    int updateByPrimaryKey(emp record);
}