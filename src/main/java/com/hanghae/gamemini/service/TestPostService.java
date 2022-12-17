package com.hanghae.gamemini.service;

import com.hanghae.gamemini.dto.TestPostRequestDto;
import com.hanghae.gamemini.dto.TestPostResponseDto;
import com.hanghae.gamemini.model.TestPost;
import com.hanghae.gamemini.model.User;
import com.hanghae.gamemini.repository.TestPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TestPostService {

    private final TestPostRepository postRepository;

    @Transactional
    public void post(TestPostRequestDto requestDto, User user) {
        postRepository.saveAndFlush(new TestPost(requestDto, user));
    }
    @Transactional(readOnly = true)
    public List<TestPostResponseDto> readPost() {
        List<TestPost> boards = postRepository.findAllByOrderByCreatedAtDesc();

        return boards.stream().map(p -> new TestPostResponseDto(p)).collect(Collectors.toList());
    }


}
