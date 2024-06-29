package org.lzq.nyy.service.impl;

import org.apache.ibatis.jdbc.Null;
import org.apache.ibatis.session.SqlSession;
import org.lzq.nyy.domain.Administrators;
import org.lzq.nyy.service.AdministratorsService;
import org.springframework.stereotype.Service;

import org.lzq.nyy.service.AdministratorsService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AdministratorsServiceImpl implements AdministratorsService {
    private SqlSession sqlSession;

    public AdministratorsServiceImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public Administrators selectEailandPassword(String administratorEmail, String administratorPassword){
        try {
            System.out.println("administratorEmail: " + administratorEmail);
            System.out.println("administratorPassword: " + administratorPassword);
            // 将参数放入一个Map中传递给selectOne方法
            Map<String, Object> params = new HashMap<>();
            params.put("administratorEmail", administratorEmail);
            params.put("administratorPassword", administratorPassword);

            // 使用 sqlSession 执行 select 语句，并传入参数
            return sqlSession.selectOne("selectEmailandPassword", params);
        } catch (Exception e) {
            // 处理异常
            e.printStackTrace();
            return null;
        }
    }
}

