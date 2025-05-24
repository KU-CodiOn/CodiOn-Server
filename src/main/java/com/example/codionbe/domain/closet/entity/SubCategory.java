package com.example.codionbe.domain.closet.entity;

import lombok.Getter;

@Getter
public enum SubCategory {
    // TOP
    TSHIRT("티셔츠"),
    SHIRT("셔츠"),
    BLOUSE("블라우스"),
    KNITWEAR("니트"),
    HOODIE("후드"),
    
    // OUTER
    JACKET("자켓"),
    COAT("코트"),
    JUMPER("점퍼"),
    CARDIGAN("가디건"),
    
    // BOTTOM
    JEANS("청바지"),
    SLACKS("슬랙스"),
    SHORTS("반바지"),
    
    // DRESS/SKIRT
    DRESS("원피스"),
    SKIRT("스커트");

    private final String displayName;
    
    SubCategory(String displayName) {
        this.displayName = displayName;
    }
} 