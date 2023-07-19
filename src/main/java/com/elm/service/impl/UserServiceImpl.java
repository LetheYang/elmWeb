package com.elm.service.impl;

import com.elm.dao.UserDao;
import com.elm.dao.impl.UserDaoImpl;
import com.elm.entity.User;
import com.elm.service.UserService;
import com.elm.utils.JDBCUtil;

import java.sql.SQLException;

public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoImpl();

    /**
     * 用户登录
     *
     * @param userId   用户id
     * @param password 用户密码
     * @return 用户id
     */
    @Override
    public User userLogin(String userId, String password) {
        try {
            return userDao.userLogin(userId, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.close();
        }
    }

    /**
     * 用户注册
     *
     * @param user 用户信息
     * @return 注册结果
     */
    @Override
    public int userRegister(User user){
        try {
            return userDao.userRegister(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JDBCUtil.close();
        }
    }
}
