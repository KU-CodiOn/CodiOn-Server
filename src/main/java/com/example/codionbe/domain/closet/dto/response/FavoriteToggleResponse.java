package com.example.codionbe.domain.closet.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FavoriteToggleResponse {
    private Long clothesId;
    private boolean isFavorite;
}
