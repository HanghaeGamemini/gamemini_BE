package com.hanghae.gamemini.security;

import com.sparta.hanghaestartproject.errorcode.UserErrorCode;
import com.sparta.hanghaestartproject.exception.RestApiException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomSecurityFilter extends OncePerRequestFilter {
     
     private final UserDetailsServiceImpl userDetailsService;
     private final PasswordEncoder passwordEncoder;
     
     //생성자
     public CustomSecurityFilter(UserDetailsServiceImpl userDetailsService, PasswordEncoder passwordEncoder){
          this.userDetailsService = userDetailsService;
          this.passwordEncoder = passwordEncoder;
     }
     
     @Override
     protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
          String username = request.getParameter("username");
          String password = request.getParameter("password");
          
          System.out.println("username = " + username);
          System.out.println("password = " + password);
          System.out.println("request.getRequestURI() = " + request.getRequestURI());
          
          
          if(username != null && password  != null && (request.getRequestURI().equals("/api/user/login") || request.getRequestURI().equals("/api/test-secured"))){
               UserDetails userDetails = userDetailsService.loadUserByUsername(username); // db에서 username 확인해서 들고옴
               
               // 비밀번호 확인
               if(!passwordEncoder.matches(password, userDetails.getPassword())) { // 일반 password , db저장된 encoding password 비교
                    throw new RestApiException(UserErrorCode.WRONG_PASSWORD);
               }
               
               // 인증 객체 생성 및 등록
               SecurityContext context = SecurityContextHolder.createEmptyContext();
               Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
               context.setAuthentication(authentication);
               
               SecurityContextHolder.setContext(context);
          }
          
          filterChain.doFilter(request,response);
     }
}