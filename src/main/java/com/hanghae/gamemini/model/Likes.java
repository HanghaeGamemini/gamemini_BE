package com.hanghae.gamemini.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.function.Supplier;

@Entity(name = "likes")
@RequiredArgsConstructor
@Getter
public class Likes extends Timestamped implements Supplier<Likes> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private boolean isLike;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    public Likes(User user, Post post) {
        this.user = user;
        this.post = post;
        this.isLike = true;
    }

    @Override
    public Likes get() {
        return null;
    }
}
