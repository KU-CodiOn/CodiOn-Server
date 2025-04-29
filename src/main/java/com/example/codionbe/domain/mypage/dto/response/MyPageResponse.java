package com.example.codionbe.domain.mypage.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MyPageResponse {
    private String email;
    private String nickname;
    private String personalColor;
}
