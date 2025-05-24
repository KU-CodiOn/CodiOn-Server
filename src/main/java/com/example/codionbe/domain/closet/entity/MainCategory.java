package com.example.codionbe.domain.closet.entity;

import lombok.Getter;

@Getter
public enum MainCategory {
    TOP("상의"),
    OUTER("아우터"),
    BOTTOM("바지"),
    DRESS("원피스/스커트");

    private final String displayName;
    
    MainCategory(String displayName) {
        this.displayName = displayName;
    }
} 