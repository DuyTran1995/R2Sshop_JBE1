package com.jbe01.r2sshop.aspect;

import com.jbe01.r2sshop.handler.error.ForbiddenException;
import com.jbe01.r2sshop.handler.error.UnauthorizedException;
import com.jbe01.r2sshop.util.JwtUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class RoleCheckAspect {
    @Autowired
    JwtUtil jwtUtil;

    @Before("@annotation(com.jbe01.r2sshop.aspect.HasRoles)")
    public void before(JoinPoint joinPoint) {

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        if (attributes == null) return;

        String getAuthorizationHeader = attributes.getRequest().getHeader("Authorization");

        if (getAuthorizationHeader == null) {
            throw new UnauthorizedException("Authorization header not found");
        }

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();

        HasRoles annotation = signature.getMethod().getAnnotation(HasRoles.class);

        boolean checkRole = this.jwtUtil.checkRoles(annotation.value());

        if (!checkRole) {
            throw new ForbiddenException("Invalid roles");
        }
    }
}
