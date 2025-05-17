package com.example.codionbe.domain.member.controller;

import com.example.codionbe.domain.member.dto.request.*;
import com.example.codionbe.domain.member.dto.response.LoginResponse;
import com.example.codionbe.domain.member.dto.response.SignUpResponse;
import com.example.codionbe.domain.member.dto.response.TokenRefreshResponse;
import com.example.codionbe.domain.member.exception.AuthSuccessCode;
import com.example.codionbe.domain.member.service.AuthService;
import com.example.codionbe.global.auth.CustomUserDetails;
import com.example.codionbe.global.common.success.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    @DeleteMapping("/logout")
    @Override
    public ResponseEntity<SuccessResponse<Void>> logout(@AuthenticationPrincipal CustomUserDetails userDetails) {
        authService.logout(userDetails.getUser().getId());
        return ResponseEntity.ok(new SuccessResponse<>(AuthSuccessCode.LOGOUT_SUCCESS, null));
    }

    @Override
    @PostMapping("/kakao")
    public ResponseEntity<SuccessResponse<LoginResponse>> kakaoLogin(@RequestBody KakaoLoginRequest request) {
        LoginResponse response = authService.kakaoLogin(request.getCode());
        return ResponseEntity.ok(new SuccessResponse<>(AuthSuccessCode.LOGIN_SUCCESS, response));
    }

    @Override
    @PatchMapping("/complete-social")
    public ResponseEntity<SuccessResponse<Void>> completeSocialSignup(
            CompleteSocialSignupRequest request,
            CustomUserDetails userDetails) {

        authService.completeSocialSignup(userDetails.getUser().getId(), request);
        return ResponseEntity.ok(new SuccessResponse<>(AuthSuccessCode.SOCIAL_SIGNUP_COMPLETE_SUCCESS, null));
    }

}
