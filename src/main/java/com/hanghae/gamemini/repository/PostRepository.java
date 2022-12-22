package com.hanghae.gamemini.repository;

import com.hanghae.gamemini.model.Post;

import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    
    Page<Post> findAllByContentContainingAndDeletedIsNullOrderByCreatedAtDesc(String search, Pageable pageable);
    Page<Post> findAllByTitleContainingAndDeletedIsFalseOrderByCreatedAtDesc(String search, Pageable pageable);
    Page<Post> findAllByAndDeletedIsFalseOrderByCreatedAtDesc(Pageable pageable);

//    List<Post> findAllByDeletedIsFalseOrderByCreatedAtDesc(Pageable pageable);
    List<Post> findAllByOrderByCreatedAtDesc(Pageable pageable);
    List<Post> findAllByUsername(String username);

    //게시글과 like 연관짓


    @Query(nativeQuery = true,
            value = "Select p.* from post p join users u on p.username = u.username "
                     +"where u.nickname like %:nickname% and p.deleted is null "
                     +"order by created_at desc",
         countQuery = "select * from (" +
                      "Select p.* from post p join users u on p.username = u.username "
                      +"where u.nickname like %:nickname% and p.deleted is null "
                      +"order by created_at desc"
                      +") as ori")
    Page<Post> findAllByUsername(@Param("nickname") String nickname, Pageable pageable);
    
    @Modifying
    @Query ("Update Post p Set p.deleted = true where p.id = :id")
    void updatePostDeleted(@Param(value="id") Long id);

    
    Optional<Post> findByIdAndUsername(Long id, String username);

    Optional<Post> findByIdAndDeletedIsFalse(Long id);

}
