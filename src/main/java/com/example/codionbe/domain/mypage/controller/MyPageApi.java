package com.example.codionbe.domain.mypage.controller;

import com.example.codionbe.domain.mypage.dto.request.UpdateProfileRequest;
import com.example.codionbe.domain.mypage.dto.response.MyPageResponse;
import com.example.codionbe.global.auth.CustomUserDetails;
import com.example.codionbe.global.common.success.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "마이페이지", description = "마이페이지 관련 API")
public interface MyPageApi {

    @Operation(summary = "마이페이지 조회 API")
    @ApiResponse(responseCode = "200", description = "회원 정보 조회 성공")
    ResponseEntity<SuccessResponse<MyPageResponse>> getMyPage(
            @Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails userDetails
    );

    @Operation(summary = "프로필 수정 API")
    @ApiResponse(responseCode = "200", description = "회원 정보 수정 성공")
    ResponseEntity<SuccessResponse<MyPageResponse>> updateProfile(
            @RequestBody UpdateProfileRequest request,
            @Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails userDetails
    );
}