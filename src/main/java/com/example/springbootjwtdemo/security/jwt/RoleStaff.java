//package com.example.springbootjwtdemo.filter;
//
//import com.example.springbootjwtdemo.security.jwt.JwtUtils;
//import com.example.springbootjwtdemo.security.service.UserDetailsServiceImpl;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//
//@Slf4j
//@Order(2)
//public class RoleStaff implements Filter {
//    @Autowired
//    private JwtUtils jwtUtils;
//    @Autowired
//    private UserDetailsServiceImpl userDetailsServiceImp;
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        String alltoken = ((HttpServletRequest) servletRequest).getHeader("Authorization");
//        if (alltoken!=null && alltoken.startsWith("Bearer ")){
//            String token = alltoken.substring(7);
//            Long userId = jwtUtils.getUserIdFromJWT(token);
//            UserDetails userDetails = userDetailsServiceImp.loadUserByUserId(userId);
//            if(userDetails != null) {
//                // Nếu người dùng hợp lệ, set thông tin cho Seturity Context
//                UsernamePasswordAuthenticationToken
//                        authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//                filterChain.doFilter(servletRequest, servletResponse);
//            }
//
//        }
//
//    }
//}
