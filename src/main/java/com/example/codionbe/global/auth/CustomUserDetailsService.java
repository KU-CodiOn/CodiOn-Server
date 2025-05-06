package com.example.codionbe.global.auth;

import com.example.codionbe.domain.member.repository.UserRepository;
import com.example.codionbe.domain.member.entity.User;
import com.example.codionbe.domain.member.exception.AuthErrorCode;
import com.example.codionbe.global.common.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetails loadUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new CustomException(AuthErrorCode.USER_NOT_FOUND));

        return new CustomUserDetails(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        throw new UnsupportedOperationException("사용하지 않음");
    }
}
