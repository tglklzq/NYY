package org.lzq.nyy.service.Impl;

import org.apache.ibatis.session.SqlSession;
import org.lzq.nyy.domain.Admins;
import org.lzq.nyy.mapper.AdminsMapper;
import org.lzq.nyy.service.AdminsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class AdminsServiceImpl implements AdminsService {
    private final SqlSession sqlSession;
    final public Integer FinalrolePermissionId = 1;//默认级别

    @Autowired
    public AdminsServiceImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }
//登录
    @Override
    public Admins selectEmailandPasswordHash(String email, String passwordHash) {
        try {
            System.out.println("Email: " + email);
            System.out.println("Password: " + passwordHash);
            // 获取 Mapper 接口的实例
            AdminsMapper mapper = sqlSession.getMapper(AdminsMapper.class);

            // 调用接口方法
            return mapper.selectByEmailandPasswordHash(email, passwordHash);
        } catch (Exception e) {
            // 处理异常
            e.printStackTrace();
            return null;
        }
    }



    //注册
    @Override
    public Admins insertRegister(String username, String email, String phoneNumber, Integer rolePermissionId, String passwordHash) {
        Admins admin = new Admins();
        admin.setUsername(username);
        admin.setEmail(email);
        admin.setPhoneNumber(phoneNumber);
        admin.setRolePermissionId(FinalrolePermissionId);
        admin.setPasswordHash(passwordHash);
        admin.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        AdminsMapper mapper = sqlSession.getMapper(AdminsMapper.class);
        mapper.insertRegister(username, email, phoneNumber, rolePermissionId, admin.getCreatedAt().toString(), passwordHash);
        return admin;
    }
}
