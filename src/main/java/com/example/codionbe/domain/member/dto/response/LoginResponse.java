package com.example.codionbe.domain.member.dto.response;

import com.example.codionbe.domain.member.dto.UserInfoDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "로그인 응답")
public class LoginResponse {

    @Schema(description = "Access Token", example = "access-token-value")
    private String accessToken;

    @Schema(description = "Refresh Token", example = "refresh-token-value")
    private String refreshToken;

    private UserInfoDto user;
}