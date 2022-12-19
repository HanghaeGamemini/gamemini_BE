package com.hanghae.gamemini.service;

import com.hanghae.gamemini.dto.PrivateResponseBody;
import com.hanghae.gamemini.errorcode.CommonStatusCode;
import com.hanghae.gamemini.exception.RestApiException;
import com.hanghae.gamemini.model.Likes;
import com.hanghae.gamemini.model.Post;
import com.hanghae.gamemini.model.User;
import com.hanghae.gamemini.repository.LikeRepository;
import com.hanghae.gamemini.repository.PostRepository;
import com.hanghae.gamemini.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final PostRepository postRepository;

    private final LikeRepository likeRepository;

    @Transactional
    public ResponseEntity<PrivateResponseBody> PostLike(Long id) {
        User user = SecurityUtil.getCurrentUser();
        Post post = postRepository.findById(id).orElseThrow(
                () -> new RestApiException(CommonStatusCode.NO_ARTICLE)
        );
        Likes like = likeRepository.findByUserIdAndPostId(user.getId(), post.getId()).orElseGet(new Likes());

        if(like == null){//좋아요최초로누를때
            post.like();
            Likes likes = new Likes(user, post);
            likeRepository.save(likes);
            return new ResponseEntity<>(new PrivateResponseBody(CommonStatusCode.POST_LIKE), HttpStatus.OK);
        } else {
            if(like.isLike()){//좋아요눌려있을때취소
                post.likeCancel();
                like.falseIsLike();
                return new ResponseEntity<>(new PrivateResponseBody(CommonStatusCode.POST_LIKE_CANCEL), HttpStatus.OK);
            } else{//안눌려있을때다시좋아요
                post.like();
                like.trueIsLike();
                return new ResponseEntity<>(new PrivateResponseBody(CommonStatusCode.POST_LIKE), HttpStatus.OK);
            }
        }
    }
}
