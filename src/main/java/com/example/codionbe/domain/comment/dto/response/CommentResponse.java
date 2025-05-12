package com.example.codionbe.domain.comment.dto.response;

import com.example.codionbe.domain.comment.entity.Comment;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "코멘트 응답")
public class CommentResponse {

    @Schema(description = "기분 상태", example = "좋음")
    private String mood;

    @Schema(description = "코멘트 내용", example = "오늘 코디 정말 마음에 들어요!")
    private String content;

    public static CommentResponse from(Comment comment) {
        return new CommentResponse(comment.getMood(), comment.getContent());
    }
}
