package org.lzq.nyy.service.Impl;

import org.apache.ibatis.session.SqlSession;
import org.lzq.nyy.domain.Admins;
import org.lzq.nyy.dto.ApiResponse;
import org.lzq.nyy.mapper.AdminsMapper;
import org.lzq.nyy.service.AdminsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            // 获取 Mapper 接口的实例
            AdminsMapper mapper = sqlSession.getMapper(AdminsMapper.class);
            // 调用接口方法
            return mapper.selectByEmailandPasswordHash(email, passwordHash);
    }



    //注册
    @Override
    public int insertRegister(String username, String email, String phoneNumber, Integer rolePermissionId, String createdAt, String passwordHash) {
        AdminsMapper adminsMapper = sqlSession.getMapper(AdminsMapper.class);
        rolePermissionId=FinalrolePermissionId;
        return adminsMapper.insertRegister(username, email, phoneNumber, rolePermissionId, createdAt, passwordHash);
    }

}
