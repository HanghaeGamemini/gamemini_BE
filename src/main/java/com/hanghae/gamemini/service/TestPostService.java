package com.hanghae.gamemini.service;

import com.hanghae.gamemini.dto.TestPostListResponseDto;
import com.hanghae.gamemini.dto.TestPostRequestDto;
import com.hanghae.gamemini.dto.TestPostResponseDto;
import com.hanghae.gamemini.model.TestPost;
import com.hanghae.gamemini.model.User;
import com.hanghae.gamemini.repository.LikeRepository;
import com.hanghae.gamemini.repository.TestPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestPostService {

    private final TestPostRepository postRepository;

    private final LikeRepository likeRepository;

    @Transactional
    public void post(TestPostRequestDto requestDto, User user) {
        postRepository.saveAndFlush(new TestPost(requestDto, user));
    }

    @Transactional(readOnly = true)
    public TestPostListResponseDto readPost(User user) {

        TestPostListResponseDto testPostListResponseDto = new TestPostListResponseDto();
        List<TestPost> posts = postRepository.findAllByOrderByCreatedAtDesc();


        for (TestPost post : posts) {
            boolean isLike = likeRepository.existsByUserAndPost(user, post);
            testPostListResponseDto.likeAdd(new TestPostResponseDto(post, isLike));
        }

//        return posts.stream().map(post -> new TestPostResponseDto(post, checkLike)).collect(Collectors.toList());
        return testPostListResponseDto;
    }


}
