package com.example.codionbe.domain.mypage.service;

import com.example.codionbe.domain.member.entity.User;
import com.example.codionbe.domain.member.exception.AuthErrorCode;
import com.example.codionbe.domain.member.repository.UserRepository;
import com.example.codionbe.domain.mypage.dto.request.UpdateProfileRequest;
import com.example.codionbe.domain.mypage.dto.response.MyPageResponse;
import com.example.codionbe.global.common.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MyPageService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public MyPageResponse getMyPage(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(AuthErrorCode.USER_NOT_FOUND));

        return new MyPageResponse(
                user.getEmail(),
                user.getNickname(),
                user.getPersonalColor()
        );
    }

    @Transactional
    public MyPageResponse updateProfile(Long userId, UpdateProfileRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(AuthErrorCode.USER_NOT_FOUND));

        user.updateProfile(request.getNickname(), request.getPersonalColor());

        return new MyPageResponse(
                user.getEmail(),
                user.getNickname(),
                user.getPersonalColor()
        );
    }
}
