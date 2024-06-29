package org.lzq.nyy.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.lzq.nyy.domain.Admins;

public interface AdminsMapper {

    Admins selectByEmailandPasswordHash(@Param("email") String email, @Param("passwordHash") String passwordHash);

    Admins selectByEmail(@Param("email") String email);

    Admins insertRegister(@Param("username") String username, @Param("email") String email,
                         @Param("phoneNumber") String phoneNumber,
                         @Param("rolePermissionId") Integer rolePermissionId,
                         @Param("createdAt") String createdAt,
                         @Param("passwordHash") String passwordHash);


}