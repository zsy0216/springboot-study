package com.tassel.config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object user = request.getSession().getAttribute("loginUser");
        if (user != null) {
            return true;
        } else {
            request.setAttribute("msg", "没有权限，请登录！");
            request.getRequestDispatcher("/index.html").forward(request, response);
            return false;
        }
    }
}
