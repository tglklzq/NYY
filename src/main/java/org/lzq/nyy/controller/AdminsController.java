package org.lzq.nyy.controller;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.lzq.nyy.config.MenuConfig;
import org.lzq.nyy.domain.Admins;
import org.lzq.nyy.dto.ApiResponse;
import org.lzq.nyy.service.AdminsService;
import org.lzq.nyy.util.JwtTokenUtil;
import org.lzq.nyy.vo.AdminsVo;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
        Admins admins = adminsService.selectByEmail(email);
        ApiResponse<Object> response;
        if (admins != null) {
            String storedHash = admins.getPasswordHash();
            boolean isPasswordMatch = verifyPassword(password, storedHash);
            if (isPasswordMatch) {
                String token = JwtTokenUtil.generateToken(email);
                int rolePermissionId = admins.getRolePermissionId();
                List<String> menus = MenuConfig.ROLE_MENU_MAP.get(rolePermissionId);
                Map<String, List<String>> menuDetails = new LinkedHashMap<>();
                if (menus != null) {
                    for (String menu : menus) {
                        menuDetails.put(menu, MenuConfig.MENU_ITEMS.get(menu));
                    }
                }
                System.out.println(menuDetails);
                response = new ApiResponse<>(200, true, "登录成功", menuDetails, token);
            } else {
                response = new ApiResponse<>(401, false, "密码错误。", null, null);
            }
        } else {
            response = new ApiResponse<>(401, false, "登录失败，请检查您的邮箱和密码。", null, null);
        }
        return response;
    }


    @PostMapping("/register")
    public ApiResponse<Object> register(@RequestBody AdminsVo adminsVo) {

        // 检查必填字段是否为空
        if (adminsVo.getUsername().isEmpty() || adminsVo.getEmail().isEmpty() || adminsVo.getPhoneNumber().isEmpty() || adminsVo.getPasswordHash().isEmpty()) {
            return new ApiResponse<>(400, false, "字段有空", null,null);
        }
        System.out.println("这是空的"+adminsVo.getPhoneNumber());

        // 检查用户是否已存在
        if (adminsService.selectByEmail(adminsVo.getEmail()) != null) {
            return  new ApiResponse<>(400, false, "用户已存在", null,null);
        }
        else{
            String createdAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            String bcryptPassword = hashPassword(adminsVo.getPasswordHash());
            int result = adminsService.insertRegister(adminsVo.getUsername(), adminsVo.getEmail(),
                    adminsVo.getPhoneNumber(), adminsVo.getRolePermissionId(), createdAt, bcryptPassword);
            if (result > 0) {
                return new ApiResponse<>(200, true, "注册成功", null,null);
            } else {
                return new ApiResponse<>(500, false, "注册失败", null,null);
            }
        }
    }
}
