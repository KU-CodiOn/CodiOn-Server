package com.example.codionbe.global.common.success;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "성공 응답 포맷")
public class SuccessResponse<T> {
    @Schema(description = "성공 코드", example = "AUTH_201")
    private final String code;
    @Schema(description = "성공 메시지", example = "로그인이 성공적으로 완료되었습니다.")
    private final String message;
    @Schema(description = "응답 데이터", nullable = true)
    private final T data;

    public SuccessResponse(SuccessCode successCode, T data) {
        this.code = successCode.getCode();
        this.message = successCode.getMessage();
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}
