package global.common.success;

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
