package com.hanghae.gamemini.repository;

import com.hanghae.gamemini.model.Comment;
import com.hanghae.gamemini.model.CommentNicknameInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
<<<<<<< HEAD
    List<Comment> findAllByUsername(String username);
=======


    List<Comment> findAllByUsername(String username);


     @Query(value = "select t.*, u.nickname from comment t join users u on t.username = u.username\n" +
          "where t.username not like '탈퇴한유저' and t.post_id = :postId  order by t.created_at desc", nativeQuery = true)
     List<CommentNicknameInterface> findAllByPostIdOrderByCreatedDesc(@Param ("postId") long id);

>>>>>>> 28aaf9afd7014d20df632bc9fd52e8de9d6f23f1

}
