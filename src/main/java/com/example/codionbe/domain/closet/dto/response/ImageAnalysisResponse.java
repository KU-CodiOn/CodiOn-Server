package com.example.codionbe.domain.closet.dto.response;

import com.example.codionbe.domain.closet.entity.Category;
import com.example.codionbe.domain.closet.entity.PersonalColor;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "이미지 분석 응답")
public class ImageAnalysisResponse {

    @Schema(description = "이미지 URL", example = "https://s3.amazonaws.com/bucket/image.jpg")
    private String imageUrl;

    @Schema(description = "카테고리", example = "TOP")
    private Category category;

    @Schema(description = "퍼스널컬러", example = "SUMMER")
    private PersonalColor personalColor;

    @Schema(description = "색상", example = "베이지")
    private String color;
} 