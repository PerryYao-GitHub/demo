package com.ypy.backend.utils.interceptor;

import com.ypy.backend.common.UserStatus;
import com.ypy.backend.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Objects;

@Component  // 拦截器一定要加上这个
public class UserStatusInterceptor implements HandlerInterceptor {
    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) throws Exception {
        // 允许所有 OPTIONS 请求通过  !!!!!!!!!! 不加以下内容会影响跨域
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) return true;

        Map<String, Object> claims = (Map<String, Object>) request.getAttribute("claims");
        Integer userId = (Integer) claims.get("id");
        if (userId == null || userId <= 0) return false;
        if (Objects.equals(userMapper.checkUserNotBanned(userId), UserStatus.BANNED.getCode())) {
            response.sendError(HttpStatus.FORBIDDEN.value(), "You are banned from using this service");
            return false;
        }
        return true;
    }
}
