package com.example.codionbe.domain.member.dto.request;

import lombok.Getter;

@Getter
public class KakaoLoginRequest {
    private String code; // 카카오 인가 코드
}
