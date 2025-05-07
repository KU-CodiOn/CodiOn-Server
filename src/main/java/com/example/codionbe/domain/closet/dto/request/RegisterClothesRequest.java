package com.example.codionbe.domain.closet.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class RegisterClothesRequest {

    private String name;
    private String category;
    private String personalColor;
    private String color;
    private boolean suitableForRain;
    private List<String> situationKeywords;
}
