package com.example.codionbe.domain.closet.exception;

import com.example.codionbe.global.common.success.SuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ClosetSuccessCode implements SuccessCode {

    CLOTHES_REGISTER_SUCCESS(HttpStatus.OK, "CLOSET_001", "옷 등록에 성공했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
