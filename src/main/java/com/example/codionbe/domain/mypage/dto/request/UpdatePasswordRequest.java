package com.example.codionbe.domain.mypage.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Schema(description = "비밀번호 변경 요청")
public class UpdatePasswordRequest {

    @Schema(description = "새 비밀번호", example = "newSecurePass123!")
    private String newPassword;

    @Schema(description = "새 비밀번호 확인", example = "newSecurePass123!")
    private String newPasswordCheck;
}

