package com.example.codionbe.domain.closet.dto.response;

import com.example.codionbe.domain.closet.entity.MainCategory;
import com.example.codionbe.domain.closet.entity.PersonalColor;
import com.example.codionbe.domain.closet.entity.SubCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "의류 이미지 분석 응답")
public class ClothesAnalysisResponse {

    @Schema(description = "이미지 URL", example = "https://s3.amazonaws.com/bucket/image.jpg")
    private String imageUrl;

    @Schema(description = "메인 카테고리", example = "OUTER")
    private MainCategory mainCategory;

    @Schema(description = "서브 카테고리", example = "JACKET")
    private SubCategory subCategory;

    @Schema(description = "퍼스널컬러", example = "WINTER")
    private PersonalColor personalColor;

    @Schema(description = "색상", example = "Black")
    private String color;
} 