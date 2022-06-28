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
//@Order(1)
//public class RoleStaff implements Filter {
//    @Autowired
//    private JwtUtils jwtUtils;
//    @Autowired
//    private UserDetailsServiceImpl userDetailsServiceImp;
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//        try {
//            // Lấy jwt từ request
//            String jwt = getJwtFromRequest(request);
//            System.out.println(jwt);
//
//            if (jwt!=null && jwtUtils.validateToken(jwt)) {
//                // Lấy id user từ chuỗi jwt
////                Long userId = jwtUtils.getUserIdFromJWT(jwt);
////                System.out.println("d: "+userId);
//                // Lấy thông tin người dùng từ id
////                UserDetails userDetails = userDetailsServiceImp.loadUserByUserId(userId);
//                int a = userDetails.getAuthorities().toString().length();
//                String role = userDetails.getAuthorities().toString().substring(1,a-1);
//                if(userDetails != null && role.equals("ROLE_STAFF")) {
//                    // Nếu người dùng hợp lệ, set thông tin cho Seturity Context
//                    UsernamePasswordAuthenticationToken
//                            authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//
//                    SecurityContextHolder.getContext().setAuthentication(authentication);
//                }
//            }
//        } catch (Exception ex) {
//            log.error("failed on set user authentication", ex);
//        }
//
//        filterChain.doFilter(request, response);
//
//    }
//    private String getJwtFromRequest(HttpServletRequest request) {
//        String bearerToken = request.getHeader("Authorization");
//        // Kiểm tra xem header Authorization có chứa thông tin jwt không
//        if (bearerToken!=null && bearerToken.startsWith("Bearer ")) {
//            return bearerToken.substring(7);
//        }
//        return null;
//    }
//}
