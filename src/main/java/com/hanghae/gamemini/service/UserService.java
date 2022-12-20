package com.hanghae.gamemini.service;


import com.hanghae.gamemini.dto.LoginRequestDto;
import com.hanghae.gamemini.dto.SignupRequestDto;
import com.hanghae.gamemini.dto.tempLoginResponseDto;
import com.hanghae.gamemini.errorcode.UserStatusCode;
import com.hanghae.gamemini.exception.RestApiException;
import com.hanghae.gamemini.jwt.JwtUtil;
import com.hanghae.gamemini.model.User;
import com.hanghae.gamemini.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final JwtUtil jwtUtil;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void signUp(SignupRequestDto requestDto) {
        String pw = requestDto.getPassword();
        String pwdCheck = requestDto.getPasswordCheck();
        //비밀번호체크
        if(!pw.equals(pwdCheck)){
            throw new RestApiException(UserStatusCode.PASSWORD_CHECK);
        }
        Optional<User> found = userRepository.findByUsername(requestDto.getUsername());
        if (found.isPresent()){
            throw new RestApiException(UserStatusCode.OVERLAPPED_USERNAME);
        }


        String password = passwordEncoder.encode(requestDto.getPassword());

        userRepository.save(new User(requestDto, password));
    }

    @Transactional
    public tempLoginResponseDto login(LoginRequestDto loginRequestDto, HttpServletResponse response) {
        String username = loginRequestDto.getUsername();
        String password = loginRequestDto.getPassword();

        // 사용자 확인
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new RestApiException(UserStatusCode.NO_USER)
        );
        // 비밀번호 확인
        if(!passwordEncoder.matches(password, user.getPassword())){
            throw  new RestApiException(UserStatusCode.WRONG_PASSWORD);
        }
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.getUsername()));
        return new tempLoginResponseDto(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.getUsername()));
    }
}
