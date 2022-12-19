package com.hanghae.gamemini.service;

import com.hanghae.gamemini.dto.PostResponseDto;
import com.hanghae.gamemini.dto.PrivateResponseBody;
import com.hanghae.gamemini.errorcode.CommonStatusCode;
import com.hanghae.gamemini.exception.RestApiException;
import com.hanghae.gamemini.model.User;
import com.hanghae.gamemini.dto.PostRequestDto;
import com.hanghae.gamemini.model.Post;
import com.hanghae.gamemini.repository.PostRepository;

import org.apache.catalina.security.SecurityUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

public class PostService {

     private PostRepository postRepository;

     //전체글 조회
     @Transactional(readOnly = true)
     public List<PostResponseDto> getPsot() {
          List<Post> posts = postRepository.findAllByOrderByCreatedAtDesc();

          List<PostResponseDto> postResponseDtos = new ArrayList<>();

          for (Post post : posts) {
               PostResponseDto postResponseDto = new PostResponseDto(post);

               postResponseDtos.add(postResponseDto);
          }
          return postResponseDtos;
     }



     //글 선택 조회
     @Transactional(readOnly = true)
     public PostResponseDto detailPost(Long id){
          Post post = postRepository.findById(id).orElseThrow(
                  () -> new RestApiException(CommonStatusCode.NO_ARTICLE)
          );
          return new PostResponseDto(post);
     }

     //게시글 작성

     @Transactional
     public void createPost(PostRequestDto postRequestDto, User user) {
          postRepository.saveAndFlush(new Post(postRequestDto, user));
     }

     //게시글 수정
     public String updatePost(Long id, PostRequestDto postRequestDto, User user) {
          Post post = postRepository.findById(id).orElseThrow(
                  () -> new RestApiException(CommonStatusCode.NO_ARTICLE)
          );
          if(post.getUser().getUsername().equals(user.getUsername())){
               post.update(postRequestDto);
          }
          return CommonStatusCode.OK.getStatusMsg();
     }
     //게시글 삭제

     @Transactional
     public String deletePost(Long id, User user){

          Post post = postRepository.findById(id).orElseThrow(
                  () -> new RestApiException(CommonStatusCode.NO_ARTICLE)
          );
          if(post.getUser().getUsername().equals(user.getUsername())){
               postRepository.deleteById(id);
          }
          return CommonStatusCode.OK.getStatusMsg();

     }
}


