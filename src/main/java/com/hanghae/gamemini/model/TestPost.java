package com.hanghae.gamemini.model;

import com.hanghae.gamemini.dto.TestPostRequestDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity(name = "testpost")
@RequiredArgsConstructor
@Getter
public class TestPost extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private int likes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    

    public TestPost(TestPostRequestDto requestDto, User user) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.user = user;
    }

    public void like() {
        this.likes += 1;
    }

    public void likeCancel() {
        this.likes -= 1;
    }


}
