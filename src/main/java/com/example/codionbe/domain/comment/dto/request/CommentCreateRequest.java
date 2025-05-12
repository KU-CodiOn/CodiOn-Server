package com.example.codionbe.domain.comment.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Schema(description = "코멘트 생성 요청")
public class CommentCreateRequest {

    @Schema(description = "코디 날짜", example = "2024-05-12")
    private LocalDate date;

    @Schema(description = "기분 상태", example = "좋음") // "좋음", "보통", "나쁨"
    private String mood;

    @Schema(description = "코멘트 내용", example = "오늘 코디 정말 마음에 들어요!")
    private String content;
}