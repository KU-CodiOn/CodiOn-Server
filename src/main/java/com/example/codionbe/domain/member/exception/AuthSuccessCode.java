package com.example.codionbe.domain.member.exception;

import com.example.codionbe.global.common.success.SuccessCode;
import org.springframework.http.HttpStatus;

public enum AuthSuccessCode implements SuccessCode {

    SIGNUP_SUCCESS(HttpStatus.OK, "AUTH_200", "회원가입이 성공적으로 완료되었습니다."),
    LOGIN_SUCCESS(HttpStatus.OK, "AUTH_201", "로그인이 성공적으로 완료되었습니다."),
    TOKEN_REFRESH_SUCCESS(HttpStatus.OK, "AUTH_202", "토큰이 성공적으로 재발급되었습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;

    AuthSuccessCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    @Override
    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
