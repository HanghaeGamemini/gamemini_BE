package com.hanghae.gamemini.dto;

import com.hanghae.gamemini.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateProfileResponseDto {
    private String nickname;
    private String profileUrl;

    public UpdateProfileResponseDto(User user) {
        this.nickname = user.getNickname();
        this.profileUrl = user.getProfileUrl();
    }
}
