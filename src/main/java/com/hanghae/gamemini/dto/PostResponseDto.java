package com.hanghae.gamemini.dto;

import com.hanghae.gamemini.model.Post;
import com.hanghae.gamemini.model.User;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class PostResponseDto {
    
    @Getter
    @AllArgsConstructor
    public static class AllPostResponseDtoWithTotalPage {
        private int totalPage;
        private List<AllPostResponseDto> postList;
    }
    
    
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
    
        public AllPostResponseDto(Post post, boolean isLike, String nickName) {
            this.id = post.getId();
            this.title = post.getTitle();
            this.content = post.getContent();
            this.imgUrl = post.getImgUrl();
            this.nickName = nickName;
            this.isLike = isLike;
            this.likes = post.getLikes(); // 수정필요
            this.commentsNum = post.getComments().size();  // todo n+1 없도록 수정필요 groupby
            this.CreatedAt = post.getCreatedAt();
            this.ModifiedAt = post.getModifiedAt();
        }
    }
    
    @Getter
    @NoArgsConstructor
    public static class DetailResponse {
    
        private Long id;
        private String profileUrl;
        private String title;
        private String content;
        private String imgUrl;
        private String nickName;
        private List<CommentResponseDto> commentList;
        private boolean isLike;
        private int likes;
        private LocalDateTime CreatedAt;
        private LocalDateTime ModifiedAt;
    
        public DetailResponse(Post post, boolean isLike,User author) {
            this.id = post.getId();
            this.profileUrl = author.getProfileUrl();
            this.title = post.getTitle();
            this.content = post.getContent();
            this.imgUrl = post.getImgUrl();
            this.nickName = author.getNickname();
            //Todo N+1 해결하기
            this.commentList = post.getComments().stream().map(CommentResponseDto::new).collect(Collectors.toList());
            this.isLike = isLike;
            this.likes = post.getLikes(); // 수정필요
            this.CreatedAt = post.getCreatedAt();
            this.ModifiedAt = post.getModifiedAt();
        }
    }
    @Getter
    @NoArgsConstructor
    public static class createResponse {
        private Long id;
        private String title;
        private String content;
        private String imgUrl;
        private String nickName;
        private int likes;
        private LocalDateTime CreatedAt;
        private LocalDateTime ModifiedAt;
    
        public createResponse(Post post, String nickName) {
            this.id = post.getId();
            this.title = post.getTitle();
            this.content = post.getContent();
            this.imgUrl = post.getImgUrl();
            this.nickName = nickName;
            this.likes = post.getLikes(); // 수정필요
            this.CreatedAt = post.getCreatedAt();
            this.ModifiedAt = post.getModifiedAt();
        }
    }
    
    @Getter
    @NoArgsConstructor
    public static class getUpdateResponse {
        
        private Long id;
        private String profileUrl;
        private String title;
        private String content;
        private String imgUrl;
        private String nickName;
        private LocalDateTime CreatedAt;
        private LocalDateTime ModifiedAt;
        
        public getUpdateResponse(Post post, User author) {
            this.id = post.getId();
            this.profileUrl = author.getProfileUrl();
            this.title = post.getTitle();
            this.content = post.getContent();
            this.imgUrl = post.getImgUrl();
            this.nickName = author.getNickname();
            this.CreatedAt = post.getCreatedAt();
            this.ModifiedAt = post.getModifiedAt();
        }
    }
    

}


