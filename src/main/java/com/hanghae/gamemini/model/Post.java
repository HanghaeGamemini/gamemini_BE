package com.hanghae.gamemini.model;

import com.hanghae.gamemini.dto.PostRequestDto;
import io.swagger.annotations.Api;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Api(tags="게시글 컨트롤러")
@NoArgsConstructor
@Entity
@Getter
public class Post extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column
    private String imgUrl;

    @Column
    private int likes;  // todo 제거필요
    
    @Column
    private String username;
    
    @Column
    private Boolean deleted;
    
    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    @OrderBy("id asc")
    List<Comment> comments = new ArrayList<>();
    
    // img 없는경우
    public Post(PostRequestDto postRequestDto, String username) {
        this.title = postRequestDto.getTitle();
        this.content = postRequestDto.getContent();
        this.username = username;
    }

    // img 있는경우
    public Post(PostRequestDto postRequestDto, String username, String imgUrl) {
        this.title = postRequestDto.getTitle();
        this.content = postRequestDto.getContent();
        this.username = username;
        this.imgUrl = imgUrl;
    }


    public void update(PostRequestDto postRequestDto, String imgUrl){
        this.title = postRequestDto.getTitle();
        this.content = postRequestDto.getContent();
        this.imgUrl = imgUrl;
    }

    public void like() {
        this.likes += 1;
    }

    public void likeCancel() {
        this.likes -= 1;
    }


    public void nicknameUpdate(String 탈퇴한사용자) {


    }
}
