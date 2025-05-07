package com.example.codionbe.domain.coordination.exception;

import com.example.codionbe.global.common.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CoordinationErrorCode implements ErrorCode {
    INVALID_CLOTHES(HttpStatus.BAD_REQUEST, "COORD_001", "유효하지 않은 옷 정보입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
