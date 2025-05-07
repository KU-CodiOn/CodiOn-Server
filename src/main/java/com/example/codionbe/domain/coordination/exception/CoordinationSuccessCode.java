package com.example.codionbe.domain.coordination.exception;

import com.example.codionbe.global.common.success.SuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CoordinationSuccessCode implements SuccessCode {
    COORDINATION_CREATE_SUCCESS(HttpStatus.OK, "COORD_001", "코디 등록에 성공했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
