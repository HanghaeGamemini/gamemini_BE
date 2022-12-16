package com.hanghae.gamemini.service;

import com.hanghae.gamemini.model.User;
import com.hanghae.gamemini.dto.MsgResponseDto;
import com.hanghae.gamemini.dto.PostListDto;
import com.hanghae.gamemini.dto.PostRequestDto;
import com.hanghae.gamemini.dto.PostResponseDto;
import com.hanghae.gamemini.model.Post;
import com.hanghae.gamemini.repository.PostRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

public class PostService {

    private PostRepository postRepository;

    @Transactional(readOnly = true)
    public List<PostListDto> readPsot() {
        List<Post> posts = postRepository.findAllByOrderByCreatedAtDesc();
       return posts.stream().map(b -> new BoardCommentListDto(b)).collect(Collectors.toList());
    }

    //게시글 작성
    @Transactional
    public ResponseEntity<?> createPost(PostRequestDto postRequestDto, User user){
            Post post = postRepository.save(new Post(postRequestDto, user));
         return ResponseEntity.ok(new PostResponseDto(post),
                 new MsgResponseDto("게시글 작성 성공",200);

        //Return ResponseEntity.ok(new MsgResponseDto("게시글 작성 성공",200));
    }

                //게시글 수정
        public ResponseEntity<?> updatePost(Long id, PostRequestDto postRequestDto, User user) {
        Post post = postRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("없는 게시판입니다")
        );
        if(!post.getUser().getUsername().equals(user.getUsername())) {
            return ResponseEntity.ok(new MsgResponseDto("작성자만 수정 가능 합니다", HttpStatus.BAD_REQUEST.value()));
            }else{
                post.update(postRequestDto);
                 return ResponseEntity.ok(new PostResponseDto(post));
        }
        return ResponseEntity.ok(new MsgResponseDto("정상",0));

    }

    //게시글 삭제
    @Transactional
    public MsgResponseDto deletePost(Long id, PostRequestDto postRequestDto, User user) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시글이 없습니다")
        );
        if (!post.getUser().getUsername().equals(user.getUsername())) {
            return new MsgResponseDto("본인글만 삭제하세요", HttpStatus.BAD_REQUEST.value())
        }
        postRepository.deleteById(id);
    }
    return new  MsgResponseDto("삭제 성공",HttpStatus.OK.value());
}
