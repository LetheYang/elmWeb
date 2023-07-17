package com.elm.controller;

import com.elm.entity.User;
import com.elm.service.UserService;
import com.elm.service.impl.UserServiceImpl;
import com.elm.utils.Result;

import javax.servlet.http.HttpServletRequest;

public class UserController {
    UserService userService = new UserServiceImpl();

    public Result userLogin(HttpServletRequest request) {
        String userId = request.getParameter("userId");
        String password = request.getParameter("password");
        User user = userService.userLogin(userId, password);
        if(user==null){
            return Result.fail("用户密码错误或用户不存在");
        }
        if (user.getDelTag() == 1) {
            return Result.ok(user);
        } else {
            return Result.fail("用户已被封禁");
        }
    }
}
