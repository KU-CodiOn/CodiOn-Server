package com.example.codionbe.global.common.exception;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "에러 응답 포맷")
public class ErrorResponse {
    @Schema(description = "요청 성공 여부", example = "false")
    private final boolean isSuccess = false;
    @Schema(description = "에러 코드", example = "AUTH_401")
    private final String code;
    @Schema(description = "에러 메시지", example = "비밀번호가 일치하지 않습니다.")
    private final String message;

    public ErrorResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
