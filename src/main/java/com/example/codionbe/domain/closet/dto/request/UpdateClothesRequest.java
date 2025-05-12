package com.example.codionbe.domain.closet.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@Schema(description = "옷 수정 요청")
public class UpdateClothesRequest {

    @Schema(description = "옷 이름", example = "린넨 셔츠")
    private String name;

    @Schema(description = "카테고리", example = "TOP")
    private String category;

    @Schema(description = "퍼스널컬러", example = "AUTUMN")
    private String personalColor;

    @Schema(description = "색상", example = "BEIGE")
    private String color;

    @Schema(description = "비 오는 날 착용 가능 여부", example = "false")
    private Boolean suitableForRain;

    @Schema(description = "상황 키워드", example = "[\"데이트\", \"여행\"]")
    private List<String> situationKeywords;
}
