package com.example.codionbe.domain.member.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TokenRefreshRequest {
    private String refreshToken;
}
