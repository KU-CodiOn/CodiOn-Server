package com.example.codionbe.domain.closet.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClothesFilterRequest {
    private String personalColor;         // 예: 봄 웜
    private String color;                 // 예: 갈색
    private List<String> situationKeywords; // 예: 데이트, 캐주얼
    private Boolean isFavorite;           // 즐겨찾기 여부
//    private String perceivedTemperature;  // 체감온도 -> 예: 가을
    private Boolean isWearableWhenRainy;  // 비 오는 날 가능 여부
}
