package com.hanghae.gamemini.service;

import com.hanghae.gamemini.dto.LoginRequestDto;
import com.hanghae.gamemini.dto.SignupRequestDto;
import com.hanghae.gamemini.errorcode.UserStatusCode;
import com.hanghae.gamemini.exception.RestApiException;
import com.hanghae.gamemini.model.User;
import com.hanghae.gamemini.jwt.JwtUtil;
import com.hanghae.gamemini.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final JwtUtil jwtUtil;

    @Transactional
    public ResponseDto signUp(SignupRequestDto requestDto) {
        Optional<User> found = userRepository.findByUsername(requestDto.getUsername());
        if (found.isPresent()){
            throw new RestApiException(UserStatusCode.OVERLAPPED_USERNAME);
//            return new ResponseDto("중복된 아이디입니다.", HttpStatus.BAD_REQUEST.value());
        }
        
//        User found1 = userRepository.findByUsername(requestDto.getUsername()).orElseThrow(
//             () -> new RestApiException(UserStatusCode.OVERLAPPED_USERNAME)
//        );

        userRepository.save(new User(requestDto));

        return new ResponseDto("회원가입 성공", HttpStatus.OK.value());
    }

    @Transactional
    public ResponseDto login(LoginRequestDto loginRequestDto, HttpServletResponse response) {
        String username = loginRequestDto.getUsername();
        String password = loginRequestDto.getPassword();

        // 사용자 확인
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("등록된 사용자가 없습니다.")
        );
        // 비밀번호 확인
        if(!user.getPassword().equals(password)){
            throw  new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.getUsername(), user.getNickname()));
        return new ResponseDto("로그인 성공", HttpStatus.OK.value());
    }
}
