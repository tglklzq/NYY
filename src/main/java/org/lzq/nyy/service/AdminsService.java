package org.lzq.nyy.service;

import org.lzq.nyy.domain.Admins;
import org.lzq.nyy.dto.ApiResponse;

public interface AdminsService {
    Admins selectEmailandPasswordHash(String email, String passwordHash);

    int insertRegister(String username, String email, String phoneNumber, Integer rolePermissionId, String createdAt, String passwordHash);
}
