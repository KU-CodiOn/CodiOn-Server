package com.example.codionbe.domain.closet.dto.request;

import com.example.codionbe.domain.closet.entity.Category;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "옷 필터 요청")
public class ClothesFilterRequest {
    @Schema(description = "대분류 카테고리", example = "OUTER") // 예: OUTER, TOP, BOTTOM, DRESS
    private Category category;

    @Schema(description = "퍼스널컬러", example = "봄 웜")
    private String personalColor;

    @Schema(description = "색상", example = "갈색")
    private String color;

    @Schema(description = "상황 키워드", example = "[\"데이트\", \"캐주얼\"]")
    private List<String> situationKeywords;

    @Schema(description = "즐겨찾기 여부", example = "true")
    private Boolean isFavorite;

    @Schema(description = "비 오는 날 착용 가능 여부", example = "false")
    private Boolean isWearableWhenRainy;
}

