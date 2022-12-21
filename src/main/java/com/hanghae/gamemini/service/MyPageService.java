package com.hanghae.gamemini.service;

import com.hanghae.gamemini.dto.UpdateProfileRequestDto;
import com.hanghae.gamemini.dto.UpdateProfileResponseDto;
import com.hanghae.gamemini.model.Comment;
import com.hanghae.gamemini.model.User;
import com.hanghae.gamemini.repository.CommentRepository;
import com.hanghae.gamemini.repository.UserRepository;
import com.hanghae.gamemini.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyPageService {

    private final UserRepository userRepository;


    private final CommentRepository commentRepository;

    @Transactional
    public UpdateProfileResponseDto updateProfile(UpdateProfileRequestDto requestDto) {
        User user = SecurityUtil.getCurrentUser();
        user.nicknameUpdate(requestDto.getNickname());
        userRepository.save(user);

        return new UpdateProfileResponseDto(user);
    }

    @Transactional
    public void deleteUser() {
        User user = SecurityUtil.getCurrentUser();
        //작성한 댓글 닉네임처리 추후 다른 방법으로 교체 후 삭제
        List<Comment> comments = commentRepository.findAllByNickname(user.getNickname());
        for(Comment comment : comments){
            comment.deletedUpdate();
        }

        user.deleteUser();
        userRepository.save(user);
    }
}
