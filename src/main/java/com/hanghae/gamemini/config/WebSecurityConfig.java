package com.hanghae.gamemini.config;

import com.hanghae.gamemini.jwt.JwtUtil;
import com.hanghae.gamemini.security.CustomAuthenticationEntryPoint;
import com.sparta.hanghaestartproject.handler.CustomAccessDeniedHandler;
import com.sparta.hanghaestartproject.jwt.JwtAuthFilter;
import com.sparta.hanghaestartproject.jwt.JwtUtil;
import com.sparta.hanghaestartproject.security.CustomAuthenticationEntryPoint;
import com.sparta.hanghaestartproject.security.UserDetailsServiceImpl;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
@EnableWebSecurity // 스프링 Security 지원을 가능하게 함
@EnableGlobalMethodSecurity (securedEnabled = true) // @Secured 어노테이션 활성화
public class WebSecurityConfig {
     
     private final JwtUtil jwtUtil;
     private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
     private final CustomAccessDeniedHandler customAccessDeniedHandler;
     private final UserDetailsServiceImpl userDetailsService;
     
     // RequiredArgsConstructor
     public WebSecurityConfig(
                              UserDetailsServiceImpl userDetailsService,
                              CustomAuthenticationEntryPoint customAuthenticationEntryPoint,
                              CustomAccessDeniedHandler customAccessDeniedHandler,
                              JwtUtil jwtUtil){
          this.userDetailsService = userDetailsService;
          this.customAuthenticationEntryPoint = customAuthenticationEntryPoint;
          this.customAccessDeniedHandler = customAccessDeniedHandler;
          this.jwtUtil = jwtUtil;
     }
     
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
     
          // todo 수정필요
          http.authorizeRequests()
               .antMatchers(HttpMethod.POST,"/api/user/**").permitAll()
               .antMatchers(HttpMethod.GET,"/api/post*/**").permitAll()
               .anyRequest().authenticated()
               //6. 서버는 JWT 토큰을 검증하고 토큰의 정보를 사용하여 사용자의 인증을 진행해주는 Spring Security 에 등록한 Custom Security Filter 를 사용하여 인증/인가를 처리한다.
               //7. Custom Security Filter에서 SecurityContextHolder 에 인증을 완료한 사용자의 상세 정보를 저장하는데 이를 통해 Spring Security 에 인증이 완료 되었다는 것을 알려준다
               .and().addFilterBefore(new JwtAuthFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);
//               ;

          // Custom 로그인 페이지 사용
          http.formLogin().loginPage("/api/user/login-page").permitAll();
          // Custom Filter 등록하기 >> 언제쓰는거?
//          http.addFilterBefore(new CustomSecurityFilter(userDetailsService, passwordEncoder()), UsernamePasswordAuthenticationFilter.class);
          // 접근 제한 페이지 이동 설정
          http.exceptionHandling().accessDeniedPage("/api/user/forbidden");
          // 401 Error 처리, Authorization 즉, 인증과정에서 실패할 시 처리
          http.exceptionHandling().authenticationEntryPoint(customAuthenticationEntryPoint);

          // 403 Error 처리, 인증과는 별개로 추가적인 권한이 충족되지 않는 경우
          http.exceptionHandling().accessDeniedHandler(customAccessDeniedHandler);
          return http.build();
     }

}
