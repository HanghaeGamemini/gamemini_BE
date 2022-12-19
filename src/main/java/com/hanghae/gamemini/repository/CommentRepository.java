package com.hanghae.gamemini.repository;

import com.hanghae.gamemini.model.Comment;
import com.hanghae.gamemini.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByUsername(User user);

}
