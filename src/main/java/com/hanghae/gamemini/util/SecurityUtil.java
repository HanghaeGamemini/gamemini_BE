package com.hanghae.gamemini.util;

import com.hanghae.gamemini.errorcode.UserStatusCode;
import com.hanghae.gamemini.exception.RestApiException;
import com.hanghae.gamemini.model.User;
import com.hanghae.gamemini.security.UserDetailsImpl;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import software.amazon.awssdk.services.codecommit.model.SymbolicLink;

@Slf4j
@NoArgsConstructor
public class SecurityUtil {
     public static Boolean isLogin() {
          final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
          if (authentication == null) {
               return false;
          }else {
               return true;
          }
     }

     
     public static User getCurrentUser() {
          final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
          if (authentication == null) {
               throw new RestApiException(UserStatusCode.NOT_FOUND_AUTHORIZATION_IN_SECURITY_CONTEXT);
          }
          
          if (authentication.getPrincipal() instanceof UserDetails) {
               UserDetailsImpl springSecurityUser = (UserDetailsImpl) authentication.getPrincipal();
               return springSecurityUser.getUser();
          }else {
               return null;
          }
     }
}

