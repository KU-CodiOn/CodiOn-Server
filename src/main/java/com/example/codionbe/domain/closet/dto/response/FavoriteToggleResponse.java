package com.example.codionbe.domain.closet.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "즐겨찾기 토글 응답")
public class FavoriteToggleResponse {

    @Schema(description = "옷 ID", example = "1")
    private Long clothesId;

    @Schema(description = "즐겨찾기 여부", example = "true")
    private boolean isFavorite;
}
