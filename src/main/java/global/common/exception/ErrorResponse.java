package global.common.exception;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "에러 응답 포맷")
public class ErrorResponse {
    private final String code;
    private final String message;

    public ErrorResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
