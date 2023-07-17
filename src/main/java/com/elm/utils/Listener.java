package com.elm.utils;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

@Slf4j
@WebListener
public class Listener implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener {

    public Listener() {
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        /* This method is called when the servlet context is initialized(when the Web application is deployed). */
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        /* This method is called when the servlet Context is undeployed or Application Server shuts down. */
        log.info("[DriverMangerListener]:-------DriverManager deregisterDriver start...");
        //获取当前以及注册jdbc驱动的枚举（多数据源下依旧可用）
        Enumeration<Driver> enumeration = DriverManager.getDrivers();

        while (enumeration.hasMoreElements()) {
            try {
                //从注册列表移除jdbc驱动
                DriverManager.deregisterDriver(enumeration.nextElement());
            } catch (SQLException e) {
                log.warn("[DriverMangerListener]:-------deregisterDriver error");
            }
        }
        log.info("[DriverMangerListener]:-------DriverManager deregisterDriver end...");
    }


    @Override
    public void sessionCreated(HttpSessionEvent se) {
        /* Session is created. */
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        /* Session is destroyed. */
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent sbe) {
        /* This method is called when an attribute is added to a session. */
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent sbe) {
        /* This method is called when an attribute is removed from a session. */
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent sbe) {
        /* This method is called when an attribute is replaced in a session. */
    }

}
