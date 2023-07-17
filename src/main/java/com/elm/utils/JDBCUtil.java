package com.elm.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @author akemihomurasama
 */
public class JDBCUtil {
    private static Properties properties;

    static {
        InputStream propertiesInput = JDBCUtil.class.getClassLoader().getResourceAsStream("JDBCConfig.properties");
        properties = new Properties();
        try {
            properties.load(propertiesInput);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String drive = properties.getProperty("jdbc.driver");
    private static String url = properties.getProperty("jdbc.url");
    private static String userName = properties.getProperty("jdbc.userName");
    private static String password = properties.getProperty("jdbc.password");
    private static final ThreadLocal<Connection> TL = new ThreadLocal<>();

    static {
        try {
            Class.forName(drive);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() {
        Connection connection = TL.get();
        if (connection == null) {
            createConnection();
            connection = TL.get();
        }
        return connection;
    }

    public static void createConnection() {
        Connection connection;
        try {
            connection = DriverManager.getConnection(url, userName, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        TL.set(connection);
    }

    /**
     * 开启事务
     */
    public static void beginTransaction() {
        Connection connection = TL.get();
        if (connection == null) {
            connection = getConnection();
        }
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void commitTransaction() {
        Connection connection = TL.get();
        try {
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void rollbackTransaction() {
        Connection con = TL.get();
        try {
            con.rollback();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void close() {
        Connection connection = TL.get();
        if (connection != null) {
            try {
                connection.close();
                TL.remove();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void close(PreparedStatement statement, Connection connection) {
        try {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void close(Connection connection, PreparedStatement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void close(ResultSet resultSet, PreparedStatement statement, Connection connection) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void close(ResultSet resultSet, PreparedStatement statement) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void close(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void close(PreparedStatement preparedStatement) {
        try {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void close(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void close(ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
