package com.example.codionbe.domain.coordination.dto.response;

import com.example.codionbe.domain.closet.entity.Clothes;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "코디에 포함된 간단한 옷 정보")
public class ClothesSimpleResponse {

    @Schema(description = "옷 ID", example = "1")
    private Long id;

    @Schema(description = "옷 이름", example = "흰색 셔츠")
    private String name;

    @Schema(description = "이미지 URL", example = "https://example-s3.com/closet/shirt.jpg")
    private String imageUrl;

    public static ClothesSimpleResponse from(com.example.codionbe.domain.closet.entity.Clothes clothes) {
        return new ClothesSimpleResponse(clothes.getId(), clothes.getName(), clothes.getImageUrl());
    }
}
