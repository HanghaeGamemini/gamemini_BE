package com.hanghae.gamemini.security;

import com.hanghae.gamemini.model.User;
import com.hanghae.gamemini.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
     
     private final UserRepository userRepository;
     
     // username으로 UserDetails 반환
     @Override
     public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
          User user = userRepository.findByUsername(username)
               .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
          
          return new UserDetailsImpl(user, user.getUsername(), user.getPassword(), user.getNickname());
     }
}