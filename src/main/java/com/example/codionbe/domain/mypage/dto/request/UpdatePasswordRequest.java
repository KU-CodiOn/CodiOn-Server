package com.example.codionbe.domain.mypage.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdatePasswordRequest {
    private String newPassword;
    private String newPasswordCheck;
}
