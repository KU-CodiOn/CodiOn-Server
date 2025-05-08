package com.example.codionbe.domain.comment.controller;

import com.example.codionbe.domain.comment.dto.CommentCreateRequest;
import com.example.codionbe.domain.comment.dto.request.CommentUpdateRequest;
import com.example.codionbe.global.auth.CustomUserDetails;
import com.example.codionbe.global.common.success.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Tag(name = "코디 기록 코멘트", description = "코디 기록 코멘트 관련 API")
public interface CommentApi {

    @Operation(summary = "코멘트 생성 API", description = "코디에 코멘트를 등록합니다.")
    @ApiResponse(responseCode = "200", description = "코멘트 등록 성공")
    @PostMapping
    ResponseEntity<SuccessResponse<Void>> createComment(
            @RequestBody CommentCreateRequest request,
            @Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails userDetails);

    @Operation(summary = "코멘트 수정 API", description = "등록된 코멘트를 수정합니다.")
    @ApiResponse(responseCode = "200", description = "코멘트 수정 성공")
    @PatchMapping
    ResponseEntity<SuccessResponse<Void>> updateComment(
            @RequestBody CommentUpdateRequest request,
            @Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails userDetails);

    @Operation(summary = "코멘트 삭제 API", description = "코디에 등록된 코멘트를 삭제합니다.")
    @ApiResponse(responseCode = "200", description = "코멘트 삭제 성공")
    @DeleteMapping
    ResponseEntity<SuccessResponse<Void>> deleteComment(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails userDetails);

}
