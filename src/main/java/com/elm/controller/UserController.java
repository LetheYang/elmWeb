package com.elm.controller;

import com.elm.entity.User;
import com.elm.service.UserService;
import com.elm.service.impl.UserServiceImpl;
import com.elm.utils.Result;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Random;

public class UserController {
    UserService userService = new UserServiceImpl();
    ObjectMapper objectMapper = new ObjectMapper();

    public Result userLogin(HttpServletRequest request) {
        String userId = request.getParameter("userId");
        String password = request.getParameter("password");
        User user = userService.userLogin(userId, password);
        if (user == null) {
            return Result.fail("用户密码错误或用户不存在");
        }
        if (user.getDelTag() == 1) {
            return Result.ok(user);
        } else {
            return Result.fail("用户已被封禁");
        }
    }

    public Result userRegister(HttpServletRequest request) {
        String userJson = request.getParameter("user");
        User user;
        //获取系统当前时间作为种子
        long seed = System.currentTimeMillis();
        Random r = new Random(seed);
        int result = r.nextInt(8999) + 1000;
        try {
            user = objectMapper.readValue(userJson, User.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        user.setUserId(String.valueOf(result));
        int i = userService.userRegister(user);
        if (i == 1) {
            return Result.ok(user);
        } else {
            return Result.fail("注册失败");
        }
    }
}
