package org.lzq.nyy.service;

import org.lzq.nyy.domain.Admins;

public interface AdminsService {
    Admins selectEmailandPasswordHash(String email, String passwordHash);

    Admins  insertRegister(String username, String email, String phoneNumber, Integer rolePermissionId, String passwordHash);

}
