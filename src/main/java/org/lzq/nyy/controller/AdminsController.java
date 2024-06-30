package org.lzq.nyy.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.lzq.nyy.domain.Admins;
import org.lzq.nyy.dto.ApiResponse;
import org.lzq.nyy.service.AdminsService;
import org.lzq.nyy.vo.AdminsVo;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api/user")
public class AdminsController {
    @Autowired
    private AdminsService adminsService;

    //加密
    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    //解密
    public boolean verifyPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }

    @PostMapping("/login")
    public ApiResponse<Object> login(@RequestBody AdminsVo adminsVo) throws JsonProcessingException {
        String email = adminsVo.getEmail();
        String password = adminsVo.getPasswordHash();
        // 根据邮箱查询数据库中存储的哈希密码
        Admins admins = adminsService.selectByEmail(email);
        ApiResponse<Object> response;
        if (admins != null) {
            String storedHash = admins.getPasswordHash();
            // 验证输入的密码是否与存储的哈希值匹配
            boolean isPasswordMatch = verifyPassword(password, storedHash);
            if (isPasswordMatch) {
                response = new ApiResponse<>(200, true, "登录成功", admins);
            } else {
                response = new ApiResponse<>(401, false, "密码错误。", null);
            }

        } else {
            System.out.println("没有查询到匹配的记录");
            response = new ApiResponse<>(401, false, "登录失败，请检查您的邮箱和密码。", null);
        }
        // 打印返回的 JSON 数据
        //System.out.println(new ObjectMapper().writeValueAsString(response));
        return response;
    }

    @PostMapping("/register")
    public ApiResponse<Object> register(@RequestBody AdminsVo adminsVo) {
        String createdAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String bcryptPassword = hashPassword(adminsVo.getPasswordHash());
        int result = adminsService.insertRegister(adminsVo.getUsername(), adminsVo.getEmail(),
                adminsVo.getPhoneNumber(), adminsVo.getRolePermissionId(), createdAt, bcryptPassword);
        if (result > 0) {
            return new ApiResponse<>(200, true, "Registration successful", null);
        } else {
            return new ApiResponse<>(500, false, "Registration failed", null);
        }
    }
}
