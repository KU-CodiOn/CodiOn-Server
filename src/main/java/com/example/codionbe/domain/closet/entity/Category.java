package com.example.codionbe.domain.closet.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Category {
    TOP("상의"),
    OUTER("아우터"),
    BOTTOM("바지"),
    DRESS("원피스"),
    SKIRT("스커트");

    private final String displayName;
} 