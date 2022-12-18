package com.hanghae.gamemini.service;

import com.hanghae.gamemini.dto.CommentRequestDto;
import com.hanghae.gamemini.dto.CommentResponseDto;
import com.hanghae.gamemini.dto.PrivateResponseBody;
import com.hanghae.gamemini.errorcode.CommonStatusCode;
import com.hanghae.gamemini.exception.RestApiException;
import com.hanghae.gamemini.model.Comment;
import com.hanghae.gamemini.model.TestPost;
import com.hanghae.gamemini.repository.CommentRepository;
import com.hanghae.gamemini.repository.TestPostRepository;
import com.hanghae.gamemini.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final TestPostRepository postRepository;

    private final CommentRepository commentRepository;

    @Transactional
    public ResponseEntity<?> postComment(Long postId, CommentRequestDto requestDto) {
        TestPost post = postRepository.findById(postId).orElseThrow(
                () -> new RestApiException(CommonStatusCode.NO_ARTICLE)
        );
        Comment save = new Comment(SecurityUtil.getCurrentUser(), post, requestDto);
        commentRepository.saveAndFlush(save);

        return ResponseEntity.ok(new CommentResponseDto(save));
    }

    public ResponseEntity<PrivateResponseBody> deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new RestApiException(CommonStatusCode.NO_COMMENT)
        );
        boolean usernameCheck = comment.getUser().getUsername().equals(SecurityUtil.getCurrentUser().getUsername());
        if (usernameCheck) {
            commentRepository.deleteById(commentId);
        } else {
            return new ResponseEntity<>(new PrivateResponseBody(CommonStatusCode.INVALID_USER), HttpStatus.BAD_REQUEST);
        }
//        commentRepository.deleteById(commentId);
        return new ResponseEntity<>(new PrivateResponseBody(CommonStatusCode.DELETE_COMMENT), HttpStatus.OK);
    }
}