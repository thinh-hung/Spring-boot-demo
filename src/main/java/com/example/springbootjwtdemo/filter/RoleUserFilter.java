//package com.example.springbootjwtdemo.filter;
//
//import com.example.springbootjwtdemo.model.User;
//import com.example.springbootjwtdemo.security.jwt.JwtUtils;
//import com.example.springbootjwtdemo.security.service.UserDetailsServiceImpl;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//import java.util.logging.LogRecord;
//
//@Component
//@Order
//@Slf4j
//public class RoleUserFilter implements Filter {
//    @Autowired
//    private JwtUtils jwtUtils;
//    @Autowired
//    private UserDetailsServiceImpl userDetailsService;
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest rep = (HttpServletRequest) servletRequest;
//        String token = rep.getHeader("Authorization").substring(7);
//        String  Username = jwtUtils.getUsernameFromToken(token);
//        if(token.equals("")){
//            log.info("No Tokens yet");
//        } else {
//            UserDetails user = userDetailsService.loadUserByUsername(Username);
//            String role = user.getAuthorities().toString();
//            switch ( role ) {
//                case  "ROLE_STAFF":
//                    log.info("Role Staff");
//                    break;
//                case  "ROLE_LEADER":
//                    log.info("Role Leader");
//                    break;
//                default:
//                    log.info("Role Funds");
//            }
//        }
//    }
//}
