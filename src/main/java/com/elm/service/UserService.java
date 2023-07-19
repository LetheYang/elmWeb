package com.elm.service;

import com.elm.entity.User;

import java.sql.SQLException;

public interface UserService {
    /**
     * 用户登录
     *
     * @param userId   用户id
     * @param password 用户密码
     * @return 用户id
     */
    User userLogin(String userId, String password);
    /**
     * 用户注册
     * @param user 用户信息
     * @return 注册结果
     */
    int userRegister(User user);
}
