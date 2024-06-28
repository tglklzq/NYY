package org.lzq.nyy.mapper;

import org.lzq.nyy.domain.Administrators;

public interface AdministratorsMapper {
    int deleteByPrimaryKey(Integer administratorid);

    int insert(Administrators record);

    int insertSelective(Administrators record);

    Administrators selectByPrimaryKey(Integer administratorid);

    int updateByPrimaryKeySelective(Administrators record);

    int updateByPrimaryKey(Administrators record);
}