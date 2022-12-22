package com.hanghae.gamemini.model;

import java.time.LocalDateTime;

public interface CommentNicknameInterface {
     
     Long getId();
     String getContent();
     Long getPostId();
     String getUsername();

     String getNickname();

     Boolean getDeleted();
     
     LocalDateTime getCreated_at();
     LocalDateTime getModified_at();
     
}
