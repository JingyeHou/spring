package com.zz.bill.config.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {

    private static final String AUTH_HEADER = "X-Auth";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader(AUTH_HEADER);
//        TODO token 验证
//        TODO token 验证失败 throw AUTH failed Exception
        return true;
    }
}
