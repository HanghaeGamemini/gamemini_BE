package com.hanghae.gamemini.repository;

import com.hanghae.gamemini.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByOrderByCreatedAtDesc();
//    Post findById(Long id, String username);

}
