package com.elm.dao.impl;

import com.elm.dao.UserDao;
import com.elm.entity.User;
import com.elm.utils.JDBCUtil;

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
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        User user = null;
        try {
            connection = JDBCUtil.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE userId=? AND password =?");
            preparedStatement.setString(1, userId);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
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
            JDBCUtil.close(connection, preparedStatement);
        }

    }
}
