package com.hanghae.gamemini.repository;

import com.hanghae.gamemini.model.Comment;
import com.hanghae.gamemini.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Id;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByUsername(String username);

}
