package com.example.codionbe.domain.closet.dto.response;

import com.example.codionbe.domain.closet.entity.Category;
import com.example.codionbe.domain.closet.entity.Clothes;
import com.example.codionbe.domain.closet.entity.SubCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
@AllArgsConstructor
@Schema(description = "옷 응답")
public class ClothesResponse {

    @Schema(description = "옷 ID", example = "1")
    private Long clothesId;

    @Schema(description = "이름", example = "화이트 셔츠")
    private String name;

    @Schema(description = "이미지 URL", example = "https://s3.amazonaws.com/bucket/image.jpg")
    private String imageUrl;

    @Schema(description = "퍼스널컬러", example = "SUMMER")
    private String personalColor;

    @Schema(description = "색상", example = "WHITE")
    private String color;

    @Schema(description = "상황 키워드", example = "[\"데이트\", \"면접\"]")
    private List<String> situationKeywords;
//    private String perceivedTemperature;
    @Schema(description = "비 오는 날 착용 가능 여부", example = "true")
    private boolean isWearableWhenRainy;

    @Schema(description = "즐겨찾기 여부", example = "false")
    private boolean isFavorite;

    @Schema(description = "착용 횟수", example = "3")
    private int wearCount;

    @Schema(description = "대분류 카테고리", example = "OUTER")
    private Category category;

    @Schema(description = "소분류 카테고리", example = "CARDIGAN")
    private SubCategory subCategory;

    public static ClothesResponse from(Clothes c) {
        return new ClothesResponse(
                c.getId(),
                c.getName(),
                c.getImageUrl(),
                c.getPersonalColor(),
                c.getColor(),
                Arrays.asList(c.getSituationKeywords().split(",")),
//                "", // 추후 perceivedTemperature 추가 시 반영
                c.isSuitableForRain(),
                c.isFavorite(),
                c.getWearCount(),
                c.getCategory(),          // <- getCategory()는 Clothes 내부에서 subCategory 기준으로 계산
                c.getSubCategory()        // <- 저장된 enum 값 그대로 전달
        );
    }
}
