package com.example.codionbe.domain.closet.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Clothes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String name; // 별명

    private String category; // "상의", "아우터", "바지", "원피스/스커트"
    private String personalColor; // 예: "봄 웜"
    private String color; // 예: "베이지", "블랙"

    private boolean suitableForRain; // 비가 오는 날에도 착용할 수 있나요?

    private String situationKeywords; // 예: "데이트,격식,출근"

    private String imageUrl;

    private boolean isFavorite = false; // 하트

    private int wearCount = 0; // 착용 빈도

    private boolean isDeleted = false; // 삭제 여부

    public void markAsDeleted() {
        this.isDeleted = true;
    }

    public void updateInfo(String name, String category, String personalColor, String color,
                           boolean suitableForRain, List<String> keywords, String imageUrl) {
        this.name = name;
        this.category = category;
        this.personalColor = personalColor;
        this.color = color;
        this.suitableForRain = suitableForRain;
        this.situationKeywords = String.join(",", keywords);
        this.imageUrl = imageUrl;
    }
}
