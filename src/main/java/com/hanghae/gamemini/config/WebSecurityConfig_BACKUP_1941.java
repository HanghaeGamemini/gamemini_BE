package com.hanghae.gamemini.config;

<<<<<<< HEAD

import com.hanghae.gamemini.jwt.JwtUtil;
=======
import com.hanghae.gamemini.jwt.JwtAuthFilter;
import com.hanghae.gamemini.jwt.JwtUtil;
import com.hanghae.gamemini.security.UserDetailsServiceImpl;
>>>>>>> f6ed22110bc03a21a8ffd17646954982b6905e55
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
<<<<<<< HEAD
=======
import org.springframework.http.HttpMethod;
>>>>>>> f6ed22110bc03a21a8ffd17646954982b6905e55
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
<<<<<<< HEAD
@RequiredArgsConstructor
@EnableWebSecurity // 스프링 Security 지원을 가능하게 함
@EnableGlobalMethodSecurity(securedEnabled = true) // @Secured 어노테이션 활성화
public class WebSecurityConfig {

    private final JwtUtil jwtUtil;


    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }


    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        // h2-console 사용 및 resources 접근 허용 설정
        return (web) -> web.ignoring()
                .requestMatchers(PathRequest.toH2Console())
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();

        // 기본 설정인 Session 방식은 사용하지 않고 JWT 방식을 사용하기 위한 설정
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests().antMatchers("/api/user/**").permitAll()
                .antMatchers("/api/board/**").permitAll()
                .antMatchers("/api/detail/**").permitAll()
                .anyRequest().authenticated()
                // JWT 인증/인가를 사용하기 위한 설정
                .and().addFilterBefore(new JwtAuthFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
=======
@EnableWebSecurity // 스프링 Security 지원을 가능하게 함
@EnableGlobalMethodSecurity (securedEnabled = true) // @Secured 어노테이션 활성화
@RequiredArgsConstructor
public class WebSecurityConfig {
     private final JwtUtil jwtUtil;
     private final UserDetailsServiceImpl userDetailsService;
     
     @Bean // 비밀번호 암호화 기능 등록
     public PasswordEncoder passwordEncoder() {
          return new BCryptPasswordEncoder();
     }
     
     @Bean
     public WebSecurityCustomizer webSecurityCustomizer() {
          // h2-console 사용 및 resources 접근 허용 설정
          return (web) -> web.ignoring()
               .requestMatchers(PathRequest.toH2Console())
               .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
     }
     
     @Bean
     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
          // CSRF 설정
          http.csrf().disable();
          
          // 기본 설정인 Session 방식은 사용하지 않고 JWT 방식을 사용하기 위한 설정
          http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
          
          http.authorizeRequests()
               // 토큰검증 필요없는 페이지 설정
               .antMatchers(HttpMethod.POST, "/api/user/**").permitAll()
               .antMatchers(HttpMethod.GET, "/api/post*/**").permitAll()
               .anyRequest().authenticated()
               //서버는 JWT 토큰을 검증하고 토큰의 정보를 사용하여 사용자의 인증을 진행해주는 Spring Security 에 등록한 JwtAuthFilter 를 사용하여 인증/인가를 처리한다.
               .and().addFilterBefore(new JwtAuthFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);
          
          // Custom 로그인 페이지 사용
          http.formLogin().loginPage("/api/user/login-page").permitAll();
          // 접근 제한 페이지 이동 설정
//          http.exceptionHandling().accessDeniedPage("/api/user/forbidden");
          
          return http.build();
     }
}
>>>>>>> f6ed22110bc03a21a8ffd17646954982b6905e55
