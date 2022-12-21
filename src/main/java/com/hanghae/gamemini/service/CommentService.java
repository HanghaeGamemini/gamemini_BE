package com.hanghae.gamemini.service;

import com.hanghae.gamemini.dto.CommentRequestDto;
import com.hanghae.gamemini.dto.CommentResponseDto;
import com.hanghae.gamemini.dto.PrivateResponseBody;
import com.hanghae.gamemini.errorcode.CommonStatusCode;
import com.hanghae.gamemini.exception.RestApiException;
import com.hanghae.gamemini.model.Comment;
import com.hanghae.gamemini.model.Post;
import com.hanghae.gamemini.repository.CommentRepository;
import com.hanghae.gamemini.repository.PostRepository;
import com.hanghae.gamemini.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final PostRepository postRepository;

    private final CommentRepository commentRepository;

    @Transactional
    public CommentResponseDto postComment(Long postId, CommentRequestDto requestDto) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new RestApiException(CommonStatusCode.NO_ARTICLE)
        );

        Comment save = new Comment(SecurityUtil.getCurrentUser(), post, requestDto);

        commentRepository.saveAndFlush(save);

        return new CommentResponseDto(save);
    }

    @Transactional
    public ResponseEntity<PrivateResponseBody> deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new RestApiException(CommonStatusCode.NO_COMMENT)
        );
        boolean usernameCheck = comment.getUsername().equals(SecurityUtil.getCurrentUser().getUsername());
        if (usernameCheck) {
//            commentRepository.updateCommentDeleted(commentId);
            commentRepository.deleteById(commentId);
        } else {
            return new ResponseEntity<>(new PrivateResponseBody(CommonStatusCode.INVALID_USER), HttpStatus.BAD_REQUEST);
        }
//        commentRepository.deleteById(commentId);
        return new ResponseEntity<>(new PrivateResponseBody(CommonStatusCode.DELETE_COMMENT), HttpStatus.OK);
    }
}