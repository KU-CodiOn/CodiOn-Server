package com.example.codionbe.domain.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "유저 정보")
public class UserInfoDto {
    private String email;
    private String nickname;
    private String personalColor;
    private boolean isSocial;
}
