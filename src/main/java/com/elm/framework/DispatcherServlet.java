package com.elm.framework;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 中央控制器
 * 分发请求，调用指定controller中指定的方法
 * 规范：请求的URI的结构为/Controller类名/被调用的方法名，比如/OrderTypeController/findAll
 * 所有请求都必须通过DispatcherServlet的处理
 * urlPatterns为'/'
 *
 * @author akemihomurasama
 */
@Slf4j
@WebServlet(name = "DispatcherServlet", value = "/")
public class DispatcherServlet extends HttpServlet {
    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        PrintWriter writer = null;
        //获取请求的URI
        String servletPath = request.getServletPath();
        //处理URI得到Controller的类名和方法名
        String className = servletPath.substring(1, servletPath.lastIndexOf("/"));
        String methodName = servletPath.substring(servletPath.lastIndexOf("/") + 1);
        //通过反射调用方法
        try {
            writer = response.getWriter();
            Class<?> aClass = Class.forName("com.elm.controller." + className);
            //调用指定方法
            Object returnVal = aClass.getMethod(methodName, HttpServletRequest.class)
                    .invoke(aClass.newInstance(), request);
            writer.print(objectMapper.writeValueAsString(returnVal));
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            writer.close();
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        doGet(request, response);
    }
}
