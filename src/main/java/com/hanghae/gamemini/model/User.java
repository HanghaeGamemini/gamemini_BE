package com.hanghae.gamemini.model;

import com.hanghae.gamemini.dto.SignupRequestDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

// @Getter의 역할.. get메소드를 대신 생성해준다. 32~46줄 참조
// @NoArgsConstructor의 역할.. 매개변수가 없는 기본 생성자를 대신 생성해준다. 15줄 참조
@Entity (name = "users")
@RequiredArgsConstructor
@Getter
public class User {
     //- username은  `최소 4자 이상, 10자 이하이며 알파벳 소문자(a~z), 숫자(0~9)`로 구성되어야 한다.
     //- password는  `최소 8자 이상, 15자 이하이며 알파벳 대소문자(a~z, A~Z), 숫자(0~9)`로 구성되어야 한다.
     //- DB에 중복된 username이 없다면 회원을 저장하고 Client 로 성공했다는 메시지, 상태코드 반환하기
     @Id
     @GeneratedValue (strategy = GenerationType.IDENTITY)
     private Long id;

     @Column (nullable = false, unique = true)
     private String username;

     @Column(nullable = false)
     private String password;

     @Column(nullable = false)
     private String nickname;
     
     @Column
     private String profileUrl;

     public User(SignupRequestDto requestDto, String password) {
          this.username = requestDto.getUsername();
          this.password = password;
          this.nickname = requestDto.getNickname();
     }
}