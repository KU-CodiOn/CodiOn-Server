package com.example.codionbe.domain.closet.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "옷 등록 요청")
public class RegisterClothesRequest {

    @Schema(description = "옷 이름", example = "화이트 셔츠")
    private String name;

    @Schema(description = "소분류 카테고리", example = "SHORT_SLEEVE")
    private String subCategory;

    @Schema(description = "퍼스널컬러", example = "SUMMER")
    private String personalColor;

    @Schema(description = "색상", example = "WHITE")
    private String color;

    @Schema(description = "이미지 URL", example = "https://s3.amazonaws.com/bucket/image.jpg")
    private String imageUrl;

    @Schema(description = "비 오는 날 착용 가능 여부", example = "true")
    private boolean suitableForRain;

    @Schema(description = "상황 키워드", example = "[\"면접\", \"소개팅\"]")
    private List<String> situationKeywords;
}
