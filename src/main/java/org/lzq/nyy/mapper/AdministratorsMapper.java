package org.lzq.nyy.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.lzq.nyy.domain.Administrators;

public interface AdministratorsMapper {
    Administrators selectEailandPassword(String Administratoremail, String Administratorpassword);
    int deleteByPrimaryKey(Integer administratorid);

    int insert(Administrators record);

    int insertSelective(Administrators record);

    Administrators selectByPrimaryKey(Integer administratorid);

    int updateByPrimaryKeySelective(Administrators record);

    int updateByPrimaryKey(Administrators record);
}