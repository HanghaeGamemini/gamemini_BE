package com.hanghae.gamemini.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
public class PostEncodeRequestDto {
     private String content;
     private String imageUrl;
     private String title;
}
