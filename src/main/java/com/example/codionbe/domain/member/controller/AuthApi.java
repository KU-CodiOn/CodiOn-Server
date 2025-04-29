package com.example.codionbe.domain.member.controller;

import com.example.codionbe.domain.member.dto.request.LoginRequest;
import com.example.codionbe.domain.member.dto.request.SignUpRequest;
import com.example.codionbe.domain.member.dto.response.LoginResponse;
import com.example.codionbe.domain.member.dto.response.SignUpResponse;
import com.example.codionbe.global.common.success.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "회원", description = "회원 관련 API")
public interface AuthApi {

    @Operation(summary = "회원가입 API")
    @ApiResponse(responseCode = "200", description = "회원가입 성공")
    ResponseEntity<SuccessResponse<SignUpResponse>> signup(SignUpRequest request);

    @Operation(summary = "로그인 API")
    @ApiResponse(responseCode = "200", description = "로그인 성공")
    ResponseEntity<SuccessResponse<LoginResponse>> login(@RequestBody LoginRequest request);
}
