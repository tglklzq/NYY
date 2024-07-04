package org.lzq.nyy.mapper;

import org.apache.ibatis.annotations.Param;
import org.lzq.nyy.domain.Admins;
import org.lzq.nyy.dto.AdminsDTO;

import java.util.List;

public interface AdminsMapper {
    Admins selectByEmailandPasswordHash(@Param("email") String email, @Param("passwordHash") String passwordHash);

    Admins selectByEmail(@Param("email") String email);

    //Integer selectAdminID(Integer adminID);

    int insertRegister(@Param("username") String username, @Param("email") String email,
                          @Param("phoneNumber") String phoneNumber,
                          @Param("rolePermissionId") Integer rolePermissionId,
                          @Param("createdAt") String createdAt,
                          @Param("passwordHash") String passwordHash);


    Admins updateByPrimaryKey(Admins admins);


    List<AdminsDTO>  showAlladmins();
    AdminsDTO selectAdminsDTO(String email);
}