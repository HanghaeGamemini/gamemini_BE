package com.hanghae.gamemini.repository;

import com.hanghae.gamemini.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    
    Page<Post> findAllByContentContainingAndDeletedIsNullOrderByCreatedAtDesc(String search, Pageable pageable);
    Page<Post> findAllByTitleContainingAndDeletedIsNullOrderByCreatedAtDesc(String search, Pageable pageable);
    Page<Post> findAllByAndDeletedIsNullOrderByCreatedAtDesc(Pageable pageable);
    
    @Query(nativeQuery = true,
            value = "Select * from post p join users u on p.username = u.username "
                     +"where u.nickname like %:nickname% and p.deleted is null "
                     +"order by created_at desc",
         countQuery = "select * from (" +
                      "Select * from post p join users u on p.username = u.username "
                      +"where u.nickname like %:nickname% and p.deleted is null "
                      +"order by created_at desc"
                      +") as ori")
    Page<Post> findAllByUsername(@Param("nickname") String nickname, Pageable pageable);
    
    
    @Modifying
    @Query ("Update Post p Set p.deleted = true where p.id = :id")
    void updatePostDeleted(@Param(value="id") Long id);
    
    Optional<Post> findByIdAndDeletedIsNull(Long id);
    
    Optional<Post> findByIdAndUsername(Long id, String username);
}
