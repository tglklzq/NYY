package org.lzq.nyy.service.impl;

import org.apache.ibatis.session.SqlSession;
import org.lzq.nyy.domain.Administrators;
import org.lzq.nyy.service.AdministratorsService;

public class AdministratorsServiceImpl implements AdministratorsService {
    private SqlSession sqlSession;
    public Administrators selectLogin(String administratorEmail, String administratorPassword,Integer roleID){
        try {
            // 使用 sqlSession 执行 select 语句，并传入参数
            return sqlSession.selectOne("selectLogin");
        } catch (Exception e) {
            // 处理异常
            e.printStackTrace();
            return null;
        }
    }
}
