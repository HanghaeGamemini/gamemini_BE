package com.hanghae.gamemini.model;

import com.hanghae.gamemini.dto.PostRequestDto;
import io.swagger.annotations.Api;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Api(tags="게시글 컨트롤러")
@NoArgsConstructor
@Entity
@Getter
public class Post extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column
    private String imgUrl;

    @Column
    private boolean isLike = false;

    @Column
    private int likes;
    
    @Column
    private String username;

    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int commentNum;

    public Post(PostRequestDto postRequestDto, String username) {
        this.title = postRequestDto.getTitle();
        this.content = postRequestDto.getContent();
        this.username = username;
    }


    public void update(PostRequestDto postRequestDto){
        this.title = postRequestDto.getTitle();
        this.content = postRequestDto.getContent();
    }

}
