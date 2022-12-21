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
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Pageable;

import java.nio.file.Path;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class MyPageService {

    private final UserRepository userRepository;

    private final S3Uploader s3Uploader;


    @Value("${part.upload.path}")
    private String uploadPath;
    public final PostRepository postRepository;
    public final CommentRepository commentRepository;

    public final LikeRepository likeRepository;

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
    @Transactional
    public ResponseEntity<PrivateResponseBody> getMyComment(User user, int page, int size) {
        List<Comment> comments = commentRepository.findAllByUsername(user.getId());
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


    //프로필 변경하기

    public String updateProfile(MultipartFile multipartFile, String realPath){
    //파일이 없을 경우
        if(multipartFile.isEmpty()) return null;
        //이미지 형식이 아닐경우
        if(multipartFile.getContentType() == null || !(multipartFile.getContentType().equals("image/png")) ||
                multipartFile.getContentType().equals("image/png"))
        throw  new RestApiException(CommonStatusCode.WRONG_IMAGE_FORMAT);

        String originalFileName = multipartFile.getOriginalFilename();
        String newFileName = createFileName(multipartFile.getOriginalFilename()); //중복되지 않는 새 파일이름
        log.info(" originalFileName : {}, newFileName : {}", originalFileName, newFileName);

        String saveName = uploadPath + newFileName;
        Path savePath = Paths.get(saveName); //파일의 저장경로를 정의함

        try{
            multipartFile.transferTo(savePath);
        } catch (IOException e) {
            log.info(""+e.getMessage());
            log.info("");
            e.printStackTrace();
            throw new RestApiException(CommonStatusCode.FILE_SAVE_FAIL);
        }
        log.info("파일수정 완료");
        return saveName;
    }



    private String createFileName(String fileName) {
        return UUID.randomUUID().toString().concat("_"+ fileName);
    }
    private String getFileExtension(String fileName){
        try{
            return fileName.substring(fileName.lastIndexOf("."));
        }catch (StringIndexOutOfBoundsException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "잘못된 형식의 파일("+fileName+")입니다");
        }
    }
}



