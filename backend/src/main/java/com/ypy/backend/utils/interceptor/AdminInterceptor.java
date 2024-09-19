package com.ypy.backend.utils.interceptor;

import com.ypy.backend.common.UserRole;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component
public class AdminInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 允许所有 OPTIONS 请求通过  !!!!!!!!!! 不加以下内容会影响跨域
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) return true;

        Map<String, Object> claims = (Map<String, Object>) request.getAttribute("claims");
        if (claims == null || !UserRole.ADMIN.getCode().equals(claims.get("role"))) {
            // 返回 403 禁止访问状态
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("You do not have permission to access this resource.");
            return false;
        }
        return true;
    }
}
