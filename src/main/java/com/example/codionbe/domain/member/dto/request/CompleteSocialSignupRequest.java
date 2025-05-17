package com.example.codionbe.domain.member.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Schema(description = "소셜 추가 정보 입력 요청")
public class CompleteSocialSignupRequest {

    @Schema(description = "닉네임", example = "홍길동")
    private String nickname;

    @Schema(description = "퍼스널 컬러", example = "SPRING")
    private String personalColor;
}
