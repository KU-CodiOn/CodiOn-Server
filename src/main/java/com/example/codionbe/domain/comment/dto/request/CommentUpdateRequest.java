package com.example.codionbe.domain.comment.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Schema(description = "코멘트 수정 요청")
public class CommentUpdateRequest {

    @Schema(description = "코디 날짜", example = "2024-05-12")
    private LocalDate date;

    @Schema(description = "기분 상태", example = "보통")
    private String mood;

    @Schema(description = "코멘트 내용", example = "기분은 평범하지만 날씨랑 잘 맞는 코디였어요.")
    private String content;
}
