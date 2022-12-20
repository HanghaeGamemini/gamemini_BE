package com.hanghae.gamemini.repository;

import com.hanghae.gamemini.model.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    
    List<Post> findAllByAndDeletedIsNullOrderByCreatedAtDesc(Pageable pageable);
    
    @Modifying
    @Query ("Update Post p Set p.deleted = true where p.id = :id")
    void updatePostDeleted(@Param(value="id") Long id);
    
    Optional<Post> findByIdAndDeletedIsNull(Long id);



    List<Post> findAllByUsername(String username);
}
