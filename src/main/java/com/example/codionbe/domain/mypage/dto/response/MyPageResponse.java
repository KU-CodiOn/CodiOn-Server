package com.example.codionbe.domain.mypage.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "마이페이지 응답")
public class MyPageResponse {

    @Schema(description = "이메일", example = "test@example.com")
    private String email;

    @Schema(description = "닉네임", example = "홍길동")
    private String nickname;

    @Schema(description = "퍼스널컬러", example = "SPRING")
    private String personalColor;
}
