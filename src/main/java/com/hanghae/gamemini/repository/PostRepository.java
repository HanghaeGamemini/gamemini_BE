package com.hanghae.gamemini.repository;

import com.hanghae.gamemini.model.Post;
import com.hanghae.gamemini.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByOrderByCreatedAtDesc(Pageable pageable);
    List<Post> findAllByUsername(String username);




}
