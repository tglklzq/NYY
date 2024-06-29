package org.lzq.nyy.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.lzq.nyy.domain.Admins;
import org.lzq.nyy.dto.ApiResponse;
import org.lzq.nyy.service.AdminsService;
import org.lzq.nyy.vo.AdminsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class AdminsController {
    @Autowired
    private AdminsService adminsService;



    @PostMapping("/login")
    public ApiResponse<Object> login(@RequestBody AdminsVo adminsVo) throws JsonProcessingException {
        String email = adminsVo.getEmail();
        String password = adminsVo.getPasswordHash();
        System.out.println("233"+email);
        System.out.println("233"+password);

        // 假设这里需要对密码进行哈希处理

        Admins admins = adminsService.selectEmailandPasswordHash(email, (String) password);
        ApiResponse<Object> response;

        if (admins != null) {
            System.out.println("这是查询出来的结果：" + admins.getEmail());
            System.out.println("这是查询出来的结果：" + admins.getPasswordHash());
            response = new ApiResponse<>(200, true, "登录成功", admins);
        } else {
            System.out.println("没有查询到匹配的记录");
            response = new ApiResponse<>(401, false, "登录失败，请检查您的邮箱和密码。", null);
        }
        // 打印返回的 JSON 数据
        System.out.println(new ObjectMapper().writeValueAsString(response));
        return response;
    }

    @PostMapping("/register")
    public ApiResponse<Object> register(@RequestBody AdminsVo adminsVo) {
        Admins admin = adminsService.insertRegister(adminsVo.getUsername(), adminsVo.getEmail(),
                adminsVo.getPhoneNumber(), adminsVo.getRolePermissionId(),
                adminsVo.getPasswordHash());

        return new ApiResponse<>(200, true, "注册成功", admin.getAdminId());
    }
}
