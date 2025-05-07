package com.example.codionbe.domain.closet.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class UpdateClothesRequest {
    private String name;
    private String category;
    private String personalColor;
    private String color;
    private Boolean suitableForRain;
    private List<String> situationKeywords;
}
