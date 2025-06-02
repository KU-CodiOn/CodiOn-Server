package com.example.codionbe.domain.closet.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SubCategory {
    // TOP
    SHORT_SLEEVE("반팔", Category.TOP),
    LONG_SLEEVE("긴팔", Category.TOP),

    // OUTER
    WINDBREAKER("바람막이", Category.OUTER),
    CARDIGAN("가디건", Category.OUTER),
    JACKET("재킷", Category.OUTER),
    PADDING("패딩", Category.OUTER),

    // BOTTOM
    SHORTS("반바지", Category.BOTTOM),
    LONG_PANTS("긴바지", Category.BOTTOM),

    // DRESS
    MINI("미니 기장", Category.DRESS),
    MIDI("미디 기장", Category.DRESS),
    LONG("롱 기장", Category.DRESS);

    private final String displayName;
    private final Category parentCategory;
}

