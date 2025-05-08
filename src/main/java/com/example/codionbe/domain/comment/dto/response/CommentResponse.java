package com.example.codionbe.domain.comment.dto.response;

import com.example.codionbe.domain.comment.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentResponse {
    private String mood;
    private String content;

    public static CommentResponse from(Comment comment) {
        return new CommentResponse(comment.getMood(), comment.getContent());
    }
}
