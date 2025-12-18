//package com.sssys.grn.framework.security;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.lang.NonNull;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
///**
// * @Description:
// * @Author: captain
// * @Date: 2025/12/18
// */
//
//
//@Component
//public class JwtAuthenticationFilter extends OncePerRequestFilter {
//
//    @Override
//    protected void doFilterInternal(
//            @NonNull HttpServletRequest request,
//            @NonNull HttpServletResponse response,
//            @NonNull FilterChain filterChain
//    ) throws ServletException, IOException {
//        // 实现JWT验证逻辑
//        String authHeader = request.getHeader("Authorization");
//        if (authHeader != null && authHeader.startsWith("Bearer ")) {
//            // 去掉 "Bearer "
//            String token = authHeader.substring(7);
//            // 在这里添加你的JWT解析和认证逻辑
//        }
//        filterChain.doFilter(request, response);
//    }
//}
