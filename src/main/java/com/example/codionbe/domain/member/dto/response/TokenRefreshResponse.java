package com.example.codionbe.domain.member.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "AccessToken 재발급 응답")
public class TokenRefreshResponse {

    @Schema(description = "새로운 Access Token", example = "new-access-token-value")
    private String accessToken;
}
