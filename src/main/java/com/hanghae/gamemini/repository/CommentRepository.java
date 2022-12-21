package com.hanghae.gamemini.repository;

import com.hanghae.gamemini.model.Comment;
import com.hanghae.gamemini.model.CommentNicknameInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {


    List<Comment> findAllByUsername(String username);


     @Query(value = "select t.*, u.nickname from comment t join users u on t.username = u.username\n" +
          "where t.username not like '삭제된유저' order by t.created_at desc", nativeQuery = true)
     List<CommentNicknameInterface> findAllByPostIdOrderByCreatedDesc(long id);


}
