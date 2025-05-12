package com.example.codionbe.domain.member.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Schema(description = "AccessToken 재발급 요청")
public class TokenRefreshRequest {

    @Schema(description = "Refresh Token", example = "refresh-token-value")
    private String refreshToken;
}

