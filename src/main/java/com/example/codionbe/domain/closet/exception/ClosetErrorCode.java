package com.example.codionbe.domain.closet.exception;

import com.example.codionbe.global.common.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ClosetErrorCode implements ErrorCode {

    CLOTHES_NOT_FOUND(HttpStatus.NOT_FOUND, "CLOSET_001", "해당 옷을 찾을 수 없습니다."),
    NO_AUTHORITY(HttpStatus.FORBIDDEN, "CLOSET_002", "해당 옷에 대한 권한이 없습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
