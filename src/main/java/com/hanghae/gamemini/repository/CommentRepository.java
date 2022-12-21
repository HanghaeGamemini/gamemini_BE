package com.hanghae.gamemini.repository;

import com.hanghae.gamemini.model.Comment;
<<<<<<< HEAD
import com.hanghae.gamemini.model.User;
=======
import com.hanghae.gamemini.model.CommentNicknameInterface;
import org.springframework.data.jpa.repository.EntityGraph;
>>>>>>> 9286fcc91b8dde4c09cbab6a5eea02f4f4d2b5be
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import javax.persistence.Id;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
<<<<<<< HEAD
    List<Comment> findAllByUsername(Long id);

=======
     
     @Query(value = "select t.*, u.nickname from comment t join users u on t.username = u.username\n" +
          "where t.username not like '삭제된유저' order by t.created_at desc", nativeQuery = true)
     List<CommentNicknameInterface> findAllByPostIdOrderByCreatedDesc(long id);
>>>>>>> 9286fcc91b8dde4c09cbab6a5eea02f4f4d2b5be
}
