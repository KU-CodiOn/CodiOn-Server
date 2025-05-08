package com.example.codionbe.domain.comment.controller;

import com.example.codionbe.domain.comment.dto.CommentCreateRequest;
import com.example.codionbe.global.auth.CustomUserDetails;
import com.example.codionbe.global.common.success.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "코디 기록 코멘트", description = "코디 기록 코멘트 관련 API")
public interface CommentApi {

    @Operation(summary = "코멘트 생성 API", description = "코디에 코멘트를 등록합니다.")
    @ApiResponse(responseCode = "200", description = "코멘트 등록 성공")
    @PostMapping
    ResponseEntity<SuccessResponse<Void>> createComment(
            @RequestBody CommentCreateRequest request,
            @Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails userDetails);

}
