package com.jbe01.r2sshop.util;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class RequestUtil {
    @Autowired
    private final JwtUtil jwtUtil;

    public RequestUtil(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    public String getTokenFromRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring("Bearer ".length());
        }
        throw new RuntimeException("Missing or invalid Authorization header");
    }



    public Long getCurrentUserId() {
        String token = this.getTokenFromRequest();
        return jwtUtil.extractUserIdFromToken(token);
    }
}
