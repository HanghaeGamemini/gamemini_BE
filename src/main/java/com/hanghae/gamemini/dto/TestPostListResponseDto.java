package com.hanghae.gamemini.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class TestPostListResponseDto {

    List<TestPostResponseDto> postList = new ArrayList<>();


    public void likeAdd(TestPostResponseDto testPostResponseDto) {
        postList.add(testPostResponseDto);
    }
}
