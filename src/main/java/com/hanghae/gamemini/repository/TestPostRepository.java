package com.hanghae.gamemini.repository;

import com.hanghae.gamemini.model.TestPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestPostRepository extends JpaRepository<TestPost, Long> {
    List<TestPost> findAllByOrderByCreatedAtDesc();
}
