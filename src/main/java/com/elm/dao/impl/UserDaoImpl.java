package com.elm.dao.impl;

import com.elm.dao.UserDao;
import com.elm.entity.User;
import com.elm.utils.JDBCUtil;
import org.apache.commons.lang3.math.NumberUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
    /**
     * 用户登录
     *
     * @param userId   用户id
     * @param password 用户密码
     * @return 用户id
     */
    @Override
    public User userLogin(String userId, String password) throws SQLException {
        Connection connection = JDBCUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE userId=? AND password =?");
            preparedStatement.setString(1, userId);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = new User();
                user.setUserId(resultSet.getString("userId"));
                user.setUserName(resultSet.getString("userName"));
                user.setId(resultSet.getInt("id"));
                user.setUserSex(resultSet.getInt("userSex"));
                user.setUserImg(resultSet.getString("userImg"));
                user.setDelTag(resultSet.getInt("delTag"));
            }
            return user;
        } finally {
            JDBCUtil.close(resultSet, preparedStatement);
        }

    }

    /**
     * 用户注册
     *
     * @param user 用户信息
     * @return 注册结果
     */
    @Override
    public int userRegister(User user) throws SQLException {
        Connection connection = JDBCUtil.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO user (userId,password,userName,userSex,delTag) values (?,?,?,?,1)");
            preparedStatement.setInt(1, NumberUtils.toInt(user.getUserId()));
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getUserName());
            preparedStatement.setInt(4, user.getUserSex());
            return preparedStatement.executeUpdate();
        } finally {
            JDBCUtil.close(connection, preparedStatement);
        }
    }
}
