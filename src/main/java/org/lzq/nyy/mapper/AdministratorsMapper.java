package org.lzq.nyy.mapper;

import org.apache.ibatis.annotations.Param;
import org.lzq.nyy.domain.Administrators;

public interface AdministratorsMapper {
    Administrators selectLogin(@Param("administratoremail") String administratoremail,
                               @Param("administratorpassword") String administratorpassword,
                               Integer roleID );
    int deleteByPrimaryKey(Integer administratorid);

    int insert(Administrators record);

    int insertSelective(Administrators record);

    Administrators selectByPrimaryKey(Integer administratorid);

    int updateByPrimaryKeySelective(Administrators record);

    int updateByPrimaryKey(Administrators record);
}