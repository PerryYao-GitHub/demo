package com.ypy.backend.utils.interceptor;

import com.ypy.backend.utils.JwtUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


@Component
public class AuthInterceptor implements HandlerInterceptor {
    /*
    * 请求体中的 Authorization: "Bearer " + this.store.user.token
    * */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 允许所有 OPTIONS 请求通过  !!!!!!!!!! 不加以下内容会影响跨域
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) return true;

        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Missing or invalid Authorization header");
            return false;
        }

        String token = authorizationHeader.substring(7);
        Map<String, Object> claims;
        try {
            claims = JwtUtils.parseToken(token);  // 这一过程包含了对token合法性检验
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Invalid token");
            return false;
        }

        request.setAttribute("claims", claims);
        return true;
    }
}
