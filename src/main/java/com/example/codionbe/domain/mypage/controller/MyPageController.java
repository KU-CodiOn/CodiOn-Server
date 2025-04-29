package com.example.codionbe.domain.mypage.controller;

import com.example.codionbe.domain.mypage.dto.request.UpdatePasswordRequest;
import com.example.codionbe.domain.mypage.dto.request.UpdateProfileRequest;
import com.example.codionbe.domain.mypage.dto.response.MyPageResponse;
import com.example.codionbe.domain.mypage.exception.MyPageSuccessCode;
import com.example.codionbe.domain.mypage.service.MyPageService;
import com.example.codionbe.global.auth.CustomUserDetails;
import com.example.codionbe.global.common.success.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class MyPageController implements MyPageApi {

    private final MyPageService myPageService;

    @Override
    @GetMapping("")
    public ResponseEntity<SuccessResponse<MyPageResponse>> getMyPage(
            @AuthenticationPrincipal CustomUserDetails userDetails) {

        MyPageResponse response = myPageService.getMyPage(userDetails.getUser().getId());
        return ResponseEntity.ok(new SuccessResponse<>(MyPageSuccessCode.MYPAGE_GET_SUCCESS, response));
    }

    @Override
    @PatchMapping("")
    public ResponseEntity<SuccessResponse<MyPageResponse>> updateProfile(
            @RequestBody UpdateProfileRequest request,
            @AuthenticationPrincipal CustomUserDetails userDetails) {

        MyPageResponse response = myPageService.updateProfile(userDetails.getUser().getId(), request);
        return ResponseEntity.ok(new SuccessResponse<>(MyPageSuccessCode.MYPAGE_UPDATE_SUCCESS, response));
    }

    @Override
    @PatchMapping("/password")
    public ResponseEntity<SuccessResponse<Void>> updatePassword(
            @RequestBody UpdatePasswordRequest request,
            @AuthenticationPrincipal CustomUserDetails userDetails) {

        myPageService.updatePassword(userDetails.getUser().getId(), request);
        return ResponseEntity.ok(new SuccessResponse<>(MyPageSuccessCode.PASSWORD_UPDATE_SUCCESS, null));
    }
}
