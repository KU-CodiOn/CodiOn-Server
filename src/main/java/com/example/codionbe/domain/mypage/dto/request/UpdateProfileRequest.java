package com.example.codionbe.domain.mypage.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Schema(description = "프로필 수정 요청")
public class UpdateProfileRequest {

    @Schema(description = "변경할 닉네임", example = "변경된닉네임")
    private String nickname;

    @Schema(description = "변경할 퍼스널컬러", example = "AUTUMN")
    private String personalColor;
}
