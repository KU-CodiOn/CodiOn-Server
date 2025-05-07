package com.example.codionbe.domain.closet.dto.response;

import com.example.codionbe.domain.closet.entity.Clothes;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
@AllArgsConstructor
public class ClothesResponse {
    private Long clothesId;
    private String name;
    private String imageUrl;
    private String category;
    private String personalColor;
    private String color;
    private List<String> situationKeywords;
//    private String perceivedTemperature;
    private boolean isWearableWhenRainy;
    private boolean isFavorite;
    private int wearCount;

    public static ClothesResponse from(Clothes c) {
        return new ClothesResponse(
                c.getId(),
                c.getName(),
                c.getImageUrl(),
                c.getCategory(),
                c.getPersonalColor(),
                c.getColor(),
                Arrays.asList(c.getSituationKeywords().split(",")),
//                "", // 추후 perceivedTemperature 추가 시 반영
                c.isSuitableForRain(),
                c.isFavorite(),
                c.getWearCount()
        );
    }
}
