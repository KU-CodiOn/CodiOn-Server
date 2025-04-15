package global.common.success;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "성공 응답 포맷")
public class SuccessResponse<T> {
    private final String code;
    private final String message;
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
