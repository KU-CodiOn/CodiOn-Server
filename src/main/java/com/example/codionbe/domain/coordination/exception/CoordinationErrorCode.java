package com.example.codionbe.domain.coordination.exception;

import com.example.codionbe.global.common.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CoordinationErrorCode implements ErrorCode {
    INVALID_CLOTHES(HttpStatus.BAD_REQUEST, "COORD_101", "유효하지 않은 옷 정보입니다."),
    COORDINATION_NOT_FOUND(HttpStatus.NOT_FOUND, "COORD_102", "해당 날짜의 코디를 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
