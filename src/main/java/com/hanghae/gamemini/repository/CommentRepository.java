package com.hanghae.gamemini.repository;

import com.hanghae.gamemini.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
