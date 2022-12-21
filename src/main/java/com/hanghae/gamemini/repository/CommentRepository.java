package com.hanghae.gamemini.repository;

import com.hanghae.gamemini.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {


//    @Modifying
//    @Query ("Update comment c Set c.deleted = true where c.id = :id")
//    void updateCommentDeleted(@Param(value="id") Long id);

    List<Comment> findAllByNickname(String nickname);

}
