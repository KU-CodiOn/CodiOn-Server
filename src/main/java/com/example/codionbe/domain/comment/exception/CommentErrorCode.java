package com.example.codionbe.domain.comment.exception;

import com.example.codionbe.global.common.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CommentErrorCode implements ErrorCode {

    COMMENT_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "COMMENT_002", "이미 코멘트가 등록되어 있습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
