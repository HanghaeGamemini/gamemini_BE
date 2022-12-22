package com.hanghae.gamemini.service;

import com.hanghae.gamemini.S3.S3Uploader;
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
import com.hanghae.gamemini.repository.UserRepository;
import com.hanghae.gamemini.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class MyPageService {

    private final UserRepository userRepository;
    
    private final S3Uploader s3Uploader;

    private final CommentRepository commentRepository;

    private final PostRepository postRepository;


    private final LikeRepository likeRepository;

    //내가 작성한 게시글 찾기
    @Transactional

    public ResponseEntity<?> getMyPost(User user, int page , int size) {
        Pageable pageable = PageRequest.of(page,size);
         postRepository.findAllByOrderByCreatedAtDesc(pageable); //페이징 처리
        List<Post> posts = postRepository.findAllByUsername(user.getUsername());

        List<PostResponseDto.AllPostResponseDto> allPostResponseDtos = new ArrayList<>();

        if (posts.isEmpty()) { //작성한 게시글이 없는 경우
            return new ResponseEntity<>(new PrivateResponseBody(CommonStatusCode.NO_ARTICLE), HttpStatus.NOT_FOUND);
        }
        for (Post post : posts) {
            //post를 하나씩 하나씩 빼서 ENTITY로 리턴
            PostResponseDto.AllPostResponseDto allPostResponseDto = new PostResponseDto.AllPostResponseDto(post);
            allPostResponseDtos.add(allPostResponseDto);
        }
        return new ResponseEntity<>(new PrivateResponseBody(CommonStatusCode.OK, allPostResponseDtos), HttpStatus.OK);
    }


    //내가 좋아요한 게시글 불러오기
    @Transactional
    public ResponseEntity<PrivateResponseBody> getMyLikePost(User user, int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        postRepository.findAllByOrderByCreatedAtDesc(pageable);
        List<Likes> likesList = likeRepository.findByUserId(user.getId()); //좋아요가 있는지 확인
        List<PostResponseDto.AllPostResponseDto> allPostResponseDtos = new ArrayList<>();

        if (likesList.isEmpty()){//좋아요한 게시글이 없는 경우
            return new ResponseEntity<>(new PrivateResponseBody(CommonStatusCode.NO_ARTICLE),HttpStatus.NOT_FOUND);
        }

        for (Likes likes : likesList) {
            Long post_id = likes.getPostId(); //좋아요한 게시글의 아이디값 얻기
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
    @Transactional
    public ResponseEntity<PrivateResponseBody> getMyComment(User user, int page, int size) {
        List<Comment> comments = commentRepository.findAllByUsername(user.getUsername());
        Pageable pageable = PageRequest.of(page,size);
        postRepository.findAllByOrderByCreatedAtDesc(pageable);

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

    @Transactional
    public UpdateProfileResponseDto updateProfile(UpdateProfileRequestDto requestDto) {
        User user = SecurityUtil.getCurrentUser();
        MultipartFile file = requestDto.getMultipartFile();
        String imgUrl = null;
        if(file != null && file.getContentType() != null) {
            imgUrl = s3Uploader.upload(file, "userImage");
        }
        user.userUpdate(requestDto.getNickname(), imgUrl);
        userRepository.save(user);

        return new UpdateProfileResponseDto(user);
    }

    @Transactional
    public void deleteUser() {
        User user = SecurityUtil.getCurrentUser();
        user.deleteUser();
        userRepository.save(user);
    }

}


