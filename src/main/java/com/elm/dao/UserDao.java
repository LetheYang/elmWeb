package com.elm.dao;

import com.elm.entity.User;

import java.sql.SQLException;

public interface UserDao {
    /**
     * 用户登录
     *
     * @param userId   用户id
     * @param password 用户密码
     * @return 用户id
     */
    User userLogin(String userId, String password) throws SQLException;

    /**
     * 用户注册
     * @param user 用户信息
     * @return 注册结果
     */
    int userRegister(User user) throws SQLException;
}
