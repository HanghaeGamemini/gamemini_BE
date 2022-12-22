package com.hanghae.gamemini.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@NoArgsConstructor
@Setter
public class UpdateProfileRequestDto {
    private String nickname;
    private MultipartFile file;
}
