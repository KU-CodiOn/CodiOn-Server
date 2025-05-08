package com.example.codionbe.domain.comment.exception;

import com.example.codionbe.global.common.success.SuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CommentSuccessCode implements SuccessCode {

    COMMENT_CREATE_SUCCESS(HttpStatus.OK, "COMMENT_001", "코멘트 등록에 성공했습니다."),
    COMMENT_UPDATE_SUCCESS(HttpStatus.OK, "COMMENT_002", "코멘트 수정에 성공했습니다.");



    private final HttpStatus status;
    private final String code;
    private final String message;
}
