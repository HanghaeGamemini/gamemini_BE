package com.hanghae.gamemini.repository;

import com.hanghae.gamemini.model.Comment;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
     
     @Query("select t, u.nickname from comment t join users u on t.username = u.username\n" +
          "where t.username not like '삭제된유저' order by t.createdAt desc")
     List<Comment> findAllByPostIdOrderByCreatedDesc(long id);
     
}
