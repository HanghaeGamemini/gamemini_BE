package com.hanghae.gamemini.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LikeResponseDto {

    private boolean isLike;

    private Long userId;

    private Long postId;

}
