package com.example.codionbe.domain.mypage.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateProfileRequest {
    private String nickname;
    private String personalColor;
}
