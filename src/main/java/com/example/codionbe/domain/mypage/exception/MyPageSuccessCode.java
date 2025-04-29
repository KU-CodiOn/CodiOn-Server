package com.example.codionbe.domain.mypage.exception;

import com.example.codionbe.global.common.success.SuccessCode;
import org.springframework.http.HttpStatus;

public enum MyPageSuccessCode implements SuccessCode {

    MYPAGE_GET_SUCCESS(HttpStatus.OK, "MYPAGE_200", "회원 정보 조회 성공"),
    MYPAGE_UPDATE_SUCCESS(HttpStatus.OK, "MYPAGE_201", "회원 정보 수정 성공"),
    PASSWORD_UPDATE_SUCCESS(HttpStatus.OK, "MYPAGE_202", "비밀번호 변경 성공"),
    USER_DELETE_SUCCESS(HttpStatus.OK, "MYPAGE_203", "회원 탈퇴 성공");

    private final HttpStatus status;
    private final String code;
    private final String message;

    MyPageSuccessCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    @Override public HttpStatus getStatus() { return status; }
    @Override public String getCode() { return code; }
    @Override public String getMessage() { return message; }
}
