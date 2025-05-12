package com.example.codionbe.domain.member.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Schema(description = "회원가입 요청")
public class SignUpRequest {

    @Schema(description = "이메일", example = "test@example.com")
    private String email;

    @Schema(description = "비밀번호", example = "securePassword123!")
    private String password;

    @Schema(description = "비밀번호 확인", example = "securePassword123!")
    private String passwordCheck;

    @Schema(description = "닉네임", example = "홍길동")
    private String nickname;

    @Schema(description = "퍼스널컬러", example = "SPRING")
    private String personalColor;
}
