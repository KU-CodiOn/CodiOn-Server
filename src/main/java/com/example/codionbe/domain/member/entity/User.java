package com.example.codionbe.domain.member.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private String nickname;

    private String personalColor;

    @Enumerated(EnumType.STRING)
    private Role role;

    public enum Role {
        USER, ADMIN
    }

    @Enumerated(EnumType.STRING)
    private SocialType socialType; // KAKAO, NONE 등

    private boolean isSocial = false; // 소셜 계정 여부

    @Column(nullable = false)
    private boolean isDeleted = false;

    public void updateProfile(String nickname, String personalColor) {
        this.nickname = nickname;
        this.personalColor = personalColor;
    }
    public void updatePassword(String encryptedPassword) {
        this.password = encryptedPassword;
    }
    public void markAsDeleted() {
        this.isDeleted = true;
    }
}
