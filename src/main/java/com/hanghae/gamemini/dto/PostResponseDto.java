package com.hanghae.gamemini.dto;

import com.hanghae.gamemini.model.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class PostResponseDto {
    
    @Getter
    @NoArgsConstructor
    public static class AllPostResponseDto {
        private Long id;
        private String title;
        private String content;
        private String imgUrl;
        private String nickName;
        private boolean isLike;
        private int likes;
        private int commentsNum;
        private LocalDateTime CreatedAt;
        private LocalDateTime ModifiedAt;
    
        public AllPostResponseDto(Post post, boolean isLike) {
            this.id = post.getId();
            this.title = post.getTitle();
            this.content = post.getContent();
            this.imgUrl = post.getImgUrl();
            this.nickName = post.getNickName();
            this.isLike = isLike;
            this.likes = post.getLikes();
            this.commentsNum = post.getComments().size();  // todo n+1 없도록 수정필요 groupby
            this.CreatedAt = post.getCreatedAt();
            this.ModifiedAt = post.getModifiedAt();
        }
    }
    
    public PostResponseDto(Post post, boolean isLike) {
        this.title = post.getTitle();
        this.content = post.getContent();
        this.comments = post.getComments().stream().map(CommentResponseDto::new).collect(Collectors.toList());
        this.CreatedAt = post.getCreatedAt();
        this.ModifiedAt = post.getModifiedAt();
        this.likes = post.getLikes();
        this.isLike = isLike;
    }
    private Long id;
    private String title;
    private String content;

    private String nickName;
    private String imgUrl;
    private List<CommentResponseDto> comments;
    private LocalDateTime CreatedAt;
    private LocalDateTime ModifiedAt;
    private int likes;

    private boolean isLike;



//    public TestPostResponseDto(TestPost post) {
//        this.title = post.getTitle();
//        this.username = post.getUser().getUsername();
//        this.content = post.getContent();
//        this.CreatedAt = post.getCreatedAt();
//        this.ModifiedAt = post.getModifiedAt();
//        this.likes = post.getLikes();
//        this.isLike = post.getLike().isLike();
//    }

}


