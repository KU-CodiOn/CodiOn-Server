package com.example.codionbe.domain.coordination.dto.response;

import com.example.codionbe.domain.closet.entity.Clothes;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ClothesSimpleResponse {
    private Long id;
    private String name;
    private String imageUrl;

    public static ClothesSimpleResponse from(Clothes clothes) {
        return new ClothesSimpleResponse(clothes.getId(), clothes.getName(), clothes.getImageUrl());
    }
}
