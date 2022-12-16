package com.hanghae.gamemini.dto;

import javax.persistence.Column;
import java.time.LocalDate;

public class PostListDto {

    private Long id;

    private String username;

    private String nickName;

    private String content;

    private String title;

    private LocalDate createAt;

    private LocalDate modefiedAt;

   private boolean isLike = true;

    private int likes;



}
