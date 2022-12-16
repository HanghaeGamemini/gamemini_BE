package com.hanghae.gamemini.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
public class PostRequestDto {

    private String title;
    private String content;
    private String imgUrl;
}
