package com.elm.service;

import com.elm.entity.User;

public interface UserService {
    /**
     * 用户登录
     *
     * @param userId   用户id
     * @param password 用户密码
     * @return 用户id
     */
    User userLogin(String userId, String password);
}