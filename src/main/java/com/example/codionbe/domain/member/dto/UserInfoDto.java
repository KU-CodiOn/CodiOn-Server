package com.example.codionbe.domain.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "유저 정보")
public class UserInfoDto {

    @Schema(description = "이메일", example = "test@kakao.com")
    private String email;

    @Schema(description = "닉네임", example = "홍길동")
    private String nickname;

    @Schema(description = "퍼스널 컬러", example = "SPRING")
    private String personalColor;

    @Schema(description = "소셜 로그인 여부", example = "true")
    private boolean isSocial;
}
