package com.example.codionbe.domain.member.service;

import com.example.codionbe.domain.UserRepository;
import com.example.codionbe.domain.member.User;
import com.example.codionbe.domain.member.dto.request.LoginRequest;
import com.example.codionbe.domain.member.dto.request.SignUpRequest;
import com.example.codionbe.domain.member.dto.response.LoginResponse;
import com.example.codionbe.domain.member.dto.response.SignUpResponse;
import com.example.codionbe.domain.member.dto.response.TokenRefreshResponse;
import com.example.codionbe.domain.member.entity.RefreshToken;
import com.example.codionbe.domain.member.exception.AuthErrorCode;
import com.example.codionbe.domain.member.repository.RefreshTokenRepository;
import com.example.codionbe.global.auth.JwtProvider;
import com.example.codionbe.global.common.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public SignUpResponse signup(SignUpRequest request) {
        // 1. 비밀번호 확인
        if (!request.getPassword().equals(request.getPasswordCheck())) {
            throw new CustomException(AuthErrorCode.PASSWORD_MISMATCH);
        }
        // 2. 이메일 중복 확인
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new CustomException(AuthErrorCode.EMAIL_ALREADY_EXISTS);
        }

        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .nickname(request.getNickname())
                .personalColor(request.getPersonalColor())
                .role(User.Role.USER)
                .build();

        userRepository.save(user);

        return new SignUpResponse(user.getId(), user.getEmail(), user.getNickname());
    }

    @Transactional
    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new CustomException(AuthErrorCode.USER_NOT_FOUND));

        // 1. 비밀번호 일치 확인
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new CustomException(AuthErrorCode.INVALID_PASSWORD);
        }

        String accessToken = jwtProvider.generateAccessToken(user);
        String refreshToken = jwtProvider.generateRefreshToken(user);

        refreshTokenRepository.save(new RefreshToken(user.getId(), refreshToken));

        return new LoginResponse(accessToken, refreshToken);
    }

    public TokenRefreshResponse refreshAccessToken(String refreshToken) {
        if (!jwtProvider.validateToken(refreshToken)) {
            throw new CustomException(AuthErrorCode.INVALID_TOKEN);
        }

        Long userId = jwtProvider.getUserId(refreshToken);
        RefreshToken savedToken = refreshTokenRepository.findByUserId(userId)
                .orElseThrow(() -> new CustomException(AuthErrorCode.UNAUTHORIZED));

        if (!savedToken.getToken().equals(refreshToken)) {
            throw new CustomException(AuthErrorCode.INVALID_TOKEN);
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(AuthErrorCode.USER_NOT_FOUND));

        String newAccessToken = jwtProvider.generateAccessToken(user);

        return new TokenRefreshResponse(newAccessToken);
    }
}
