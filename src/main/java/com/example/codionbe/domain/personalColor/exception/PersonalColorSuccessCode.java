package com.example.codionbe.domain.personalColor.exception;

import com.example.codionbe.global.common.success.SuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum PersonalColorSuccessCode implements SuccessCode {

    PERSONAL_COLOR_ANALYSIS_SUCCESS(HttpStatus.OK, "PERSONAL_COLOR_001", "퍼스널컬러 분석에 성공했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
