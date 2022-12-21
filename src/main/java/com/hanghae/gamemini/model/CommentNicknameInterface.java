package com.hanghae.gamemini.model;

import java.time.LocalDateTime;

public interface CommentNicknameInterface {
     
     Long getCommentId();
     String getContent();
     Long getPostId();
     String getUsername();
     
     String getNickname();
     
     LocalDateTime getCreated_at();
     
}
