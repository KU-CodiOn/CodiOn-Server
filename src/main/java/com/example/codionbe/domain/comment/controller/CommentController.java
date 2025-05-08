package com.example.codionbe.domain.comment.controller;

import com.example.codionbe.domain.comment.dto.CommentCreateRequest;
import com.example.codionbe.domain.comment.exception.CommentSuccessCode;
import com.example.codionbe.domain.comment.service.CommentService;
import com.example.codionbe.global.auth.CustomUserDetails;
import com.example.codionbe.global.common.success.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/coordination/comment")
public class CommentController implements CommentApi{

    private final CommentService commentService;

    @Override
    public ResponseEntity<SuccessResponse<Void>> createComment(
            CommentCreateRequest request,
            CustomUserDetails userDetails) {

        commentService.createComment(userDetails.getUser().getId(), request);
        return ResponseEntity.ok(new SuccessResponse<>(CommentSuccessCode.COMMENT_CREATE_SUCCESS, null));
    }

}
