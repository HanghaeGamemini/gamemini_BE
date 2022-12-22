package com.hanghae.gamemini.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@NoArgsConstructor
public class UpdateProfileRequestDto {
    private String nickname;
    private MultipartFile multipartFile;
}
