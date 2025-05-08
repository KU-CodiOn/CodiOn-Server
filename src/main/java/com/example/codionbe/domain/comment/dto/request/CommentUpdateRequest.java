package com.example.codionbe.domain.comment.dto.request;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class CommentUpdateRequest {
    private LocalDate date;
    private String mood;
    private String content;
}
