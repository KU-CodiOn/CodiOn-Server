package com.example.codionbe.domain.member.controller;

import com.example.codionbe.domain.member.dto.request.LoginRequest;
import com.example.codionbe.domain.member.dto.request.SignUpRequest;
import com.example.codionbe.domain.member.dto.request.TokenRefreshRequest;
import com.example.codionbe.domain.member.dto.response.LoginResponse;
import com.example.codionbe.domain.member.dto.response.SignUpResponse;
import com.example.codionbe.domain.member.dto.response.TokenRefreshResponse;
import com.example.codionbe.domain.member.exception.AuthSuccessCode;
import com.example.codionbe.domain.member.service.AuthService;
import com.example.codionbe.global.common.success.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController implements AuthApi {

    private final AuthService authService;

    @Override
    @PostMapping("/signup")
    public ResponseEntity<SuccessResponse<SignUpResponse>> signup(@RequestBody SignUpRequest request) {
        SignUpResponse response = authService.signup(request);
        return ResponseEntity.ok(new SuccessResponse<>(AuthSuccessCode.SIGNUP_SUCCESS, response));
    }

    @Override
    @PostMapping("/login")
    public ResponseEntity<SuccessResponse<LoginResponse>> login(@RequestBody LoginRequest request) {
        LoginResponse response = authService.login(request);
        return ResponseEntity.ok(new SuccessResponse<>(AuthSuccessCode.LOGIN_SUCCESS, response));
    }

    @PostMapping("/refresh")
    public ResponseEntity<SuccessResponse<TokenRefreshResponse>> refresh(@RequestBody TokenRefreshRequest request) {
        TokenRefreshResponse response = authService.refreshAccessToken(request.getRefreshToken());
        return ResponseEntity.ok(new SuccessResponse<>(AuthSuccessCode.TOKEN_REFRESH_SUCCESS, response));
    }
}
