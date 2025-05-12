package com.example.codionbe.domain.comment.exception;

import com.example.codionbe.global.common.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CommentErrorCode implements ErrorCode {

    COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "COMMENT_101", "해당 코멘트가 존재하지 않습니다."),
    COMMENT_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "COMMENT_102", "이미 코멘트가 등록되어 있습니다."),
    COORDINATION_NOT_FOUND(HttpStatus.NOT_FOUND, "COMMENT_103", "해당 날짜의 코디가 존재하지 않습니다.");


    private final HttpStatus status;
    private final String code;
    private final String message;
}
