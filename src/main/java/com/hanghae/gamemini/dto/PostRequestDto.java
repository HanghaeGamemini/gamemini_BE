package com.hanghae.gamemini.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@NoArgsConstructor
@Setter
public class PostRequestDto {
//    @ApiModelProperty (value="게시글 제목")
    private String title;
    private String content;
    private String imgUrl;
}
