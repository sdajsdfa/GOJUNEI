package com.yhgc.api.Interceptor;

import com.yhgc.api.util.TokenUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LoginInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if ((request.getMethod().toUpperCase().equals("OPTIONS"))) {
            return true;
        }
        String token = request.getHeader("Authorization");
        if (token == null){
            throw  new LoginException("请先登录");
        }else {
            int i = TokenUtil.verifyToken(token);
            if (i == 0){
                return true;
            }else {
                throw  new LoginException("请重新登录");
            }
        }
    }

}
 