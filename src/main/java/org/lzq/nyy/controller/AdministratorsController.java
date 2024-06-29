package org.lzq.nyy.controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.lzq.nyy.domain.Administrators;
import org.lzq.nyy.dto.ApiResponse;
import org.lzq.nyy.dto.ResponseDTO;
import org.lzq.nyy.service.AdministratorsService;
import org.lzq.nyy.vo.AdministratorsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/user")
public class AdministratorsController {
    @Autowired
    private AdministratorsService administratorsService;

    @PostMapping("/login")
    public ApiResponse<Object> login(@RequestBody AdministratorsVo administratorsVo) throws JsonProcessingException {
        String email = administratorsVo.getEmail();
        String password = administratorsVo.getPassword();
        Administrators admin = administratorsService.selectEailandPassword(email, password);

        ApiResponse<Object> response;
        if (admin != null) {
            System.out.println("这是查询出来的结果" + admin.getAdministratoremail());
            System.out.println("这是查询出来的结果" + admin.getAdministratorpassword());

            response = new ApiResponse<>(200, true, "登录成功", null);
        } else {
            response = new ApiResponse<>(401, false, "登录失败，请检查您的邮箱和密码。", null);
        }

        // 打印返回的 JSON 数据
        System.out.println(new ObjectMapper().writeValueAsString(response));
        return response;
    }
}
