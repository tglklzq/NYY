package org.lzq.nyy.dto;

import lombok.Data;

@Data
public class AdminsDTO {
    private String username;
    private String email;
    private int role_permission_id;
    private String phone_number;
    private String password_hash;
}
