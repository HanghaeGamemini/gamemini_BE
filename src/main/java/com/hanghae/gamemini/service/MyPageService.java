package com.hanghae.gamemini.service;

import com.hanghae.gamemini.dto.UpdateProfileRequestDto;
import com.hanghae.gamemini.dto.UpdateProfileResponseDto;
import com.hanghae.gamemini.model.Post;
import com.hanghae.gamemini.model.User;
import com.hanghae.gamemini.repository.PostRepository;
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

    private final PostRepository postRepository;

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
        userRepository.deleteById(user.getId());
        List<Post> posts = postRepository.findAllByUsername(user.getUsername());
        for(Post post : posts){
            post.nicknameUpdate();
        }


    }
}
