package org.lzq.nyy.service.Impl;

import org.apache.ibatis.session.SqlSession;
import org.lzq.nyy.domain.Admins;
import org.lzq.nyy.dto.AdminsDTO;
import org.lzq.nyy.mapper.AdminLogsMapper;
import org.lzq.nyy.mapper.AdminsMapper;
import org.lzq.nyy.service.AdminsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AdminsServiceImpl implements AdminsService {
    private static final Logger logger = LoggerFactory.getLogger(AdminsServiceImpl.class);

    private final SqlSession sqlSession;
    final public Integer FinalrolePermissionId = 1; // 默认级别

    @Autowired
    public AdminsServiceImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    // 登录
    @Override
    public Admins selectEmailandPasswordHash(String email, String passwordHash) {
        logger.info("Selecting admin with email: {} for login", email);
        AdminsMapper mapper = sqlSession.getMapper(AdminsMapper.class);
        Admins admin = mapper.selectByEmailandPasswordHash(email, passwordHash);
        if (admin != null) {
            logger.info("Admin found with email: {}", email);
        } else {
            logger.warn("No admin found with email: {}", email);
        }
        return admin;
    }

    @Override
    public Admins selectByEmail(String email) {
        logger.info("Selecting admin by email: {}", email);
        AdminsMapper mapper = sqlSession.getMapper(AdminsMapper.class);
        Admins admin = mapper.selectByEmail(email);
        if (admin != null) {
            logger.info("Admin found with email: {}", email);
        } else {
            logger.warn("No admin found with email: {}", email);
        }
        return admin;
    }

    // 更新
    @Override
    public Admins updateByPrimaryKey(Admins admins) {
        logger.info("Updating admin with id: {}", admins.getAdminId());
        AdminsMapper mapper = sqlSession.getMapper(AdminsMapper.class);
        Admins updatedAdmin = mapper.updateByPrimaryKey(admins);
        if (updatedAdmin != null) {
            logger.info("Admin with id: {} updated successfully", admins.getAdminId());
        } else {
            logger.error("Failed to update admin with id: {}", admins.getAdminId());
        }
        return updatedAdmin;
    }

    // 注册
    @Override
    public int insertRegister(String username, String email, String phoneNumber, Integer rolePermissionId, String createdAt, String passwordHash) {
        logger.info("Inserting new admin with email: {}", email);
        AdminsMapper adminsMapper = sqlSession.getMapper(AdminsMapper.class);
        rolePermissionId = FinalrolePermissionId;
        int result = adminsMapper.insertRegister(username, email, phoneNumber, rolePermissionId, createdAt, passwordHash);
        if (result > 0) {
            logger.info("Successfully inserted admin with email: {}", email);
        } else {
            logger.error("Failed to insert admin with email: {}", email);
        }
        return result;
    }

    @Override
    public List<AdminsDTO> showAlladmins() {
        logger.info("Fetching all admins");
        AdminsMapper adminsMapper = sqlSession.getMapper(AdminsMapper.class);
        List<AdminsDTO> adminsList = adminsMapper.showAlladmins();
        logger.info("Fetched {} admins", adminsList.size());
        return adminsList;
    }

    // 登录返回用户信息
    @Override
    public AdminsDTO selectAdminsDTO(String email) {
        logger.info("Selecting admin DTO by email: {}", email);
        AdminsMapper adminsMapper = sqlSession.getMapper(AdminsMapper.class);
        AdminsDTO adminDTO = adminsMapper.selectAdminsDTO(email);
        if (adminDTO != null) {
            logger.info("Admin DTO found with email: {}", email);
        } else {
            logger.warn("No admin DTO found with email: {}", email);
        }
        return adminDTO;
    }

    @Override
    public AdminsDTO personalInformation(String email) {
        logger.info("Fetching personal information for email: {}", email);
        AdminsMapper adminsMapper = sqlSession.getMapper(AdminsMapper.class);
        AdminsDTO adminInfo = adminsMapper.personalInformation(email);
        if (adminInfo != null) {
            logger.info("Personal information found for email: {}", email);
        } else {
            logger.warn("No personal information found for email: {}", email);
        }
        return adminInfo;
    }
    @Override
    public List<Map<String, Object>> getAllAdminLogs() {
        AdminLogsMapper adminLogsMapper = sqlSession.getMapper(AdminLogsMapper.class);
        return adminLogsMapper.getAllAdminLogs();
    }

}
