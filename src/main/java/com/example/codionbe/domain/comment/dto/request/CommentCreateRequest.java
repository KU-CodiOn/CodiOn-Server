package com.example.codionbe.domain.comment.dto.request;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class CommentCreateRequest {
    private LocalDate date;
    private String mood;     // "좋음", "보통", "나쁨"
    private String content;  // 상세 코멘트
}
