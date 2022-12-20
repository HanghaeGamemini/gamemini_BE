package com.hanghae.gamemini.service;


import com.hanghae.gamemini.dto.*;
import com.hanghae.gamemini.errorcode.CommonStatusCode;
import com.hanghae.gamemini.exception.RestApiException;
import com.hanghae.gamemini.model.Comment;
import com.hanghae.gamemini.model.Likes;
import com.hanghae.gamemini.model.Post;
import com.hanghae.gamemini.model.User;
import com.hanghae.gamemini.repository.CommentRepository;
import com.hanghae.gamemini.repository.LikeRepository;
import com.hanghae.gamemini.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MyPageService {

    public final PostRepository postRepository;
    public final CommentRepository commentRepository;

    public final LikeRepository likeRepository;

    //내가 작성한 게시글 찾기
    public ResponseEntity<?> getMyPost(User user) {
        List<Post> posts = postRepository.findAllByUsername(user.getUsername());
        List<PostResponseDto.AllPostResponseDto> allPostResponseDtos = new ArrayList<>();

        if (posts.isEmpty()) {
            return new ResponseEntity<>(new PrivateResponseBody(CommonStatusCode.NO_COMMENT), HttpStatus.OK);
        }
        for (Post post : posts) {
            //post를 하나씩 하나씩 빼서 ENTITY로 리턴
            PostResponseDto.AllPostResponseDto allPostResponseDto = new PostResponseDto.AllPostResponseDto(post);
            allPostResponseDtos.add(allPostResponseDto);
        }
        return new ResponseEntity<>(new PrivateResponseBody(CommonStatusCode.OK, allPostResponseDtos), HttpStatus.OK);
    }


    //내가 좋아요한 게시글 불러오기
    public ResponseEntity<PrivateResponseBody> getMyLikePost(User user) {
        List<Likes> likesList = likeRepository.findByUserId(user.getId()); //좋아요가 있는지 확인

        List<PostResponseDto.AllPostResponseDto> allPostResponseDtos = new ArrayList<>();

        for (Likes likes : likesList) {
            Long post_id = likes.getPost().getId(); //좋아요한 게시글의 아이디값 얻기
            Post post = postRepository.findById(post_id).orElseThrow( //게시글이 있는지 확인
                    () -> new RestApiException(CommonStatusCode.NO_ARTICLE)
            );
            //만약에 있으면
            PostResponseDto.AllPostResponseDto allPostResponseDto1 = new PostResponseDto.AllPostResponseDto(post);
            allPostResponseDtos.add(allPostResponseDto1);

        }
        return new ResponseEntity<>(new PrivateResponseBody(CommonStatusCode.OK, allPostResponseDtos), HttpStatus.OK);
    }


    //내가 작성한 댓글 불러오기

    public ResponseEntity<PrivateResponseBody> getMyComment(User user) {
        List<Comment> comments = commentRepository.findAllByUsername(user.getId());

        List<CommentResponseDto> commentResponseDtos = new ArrayList<>();

        if (comments.isEmpty()) {
            return new ResponseEntity<>(new PrivateResponseBody(CommonStatusCode.NO_COMMENT), HttpStatus.OK);
        }
        for (Comment comment : comments) {
            CommentResponseDto commentResponseDto = new CommentResponseDto(comment);
            commentResponseDtos.add(commentResponseDto);

        }
        return new ResponseEntity<>(new PrivateResponseBody(CommonStatusCode.OK, commentResponseDtos), HttpStatus.OK);
    }
}

//    //프로필 변경하기
//
//    public ResponseEntity<PrivateResponseBody> changeProfile(){
//
//    }
//}



