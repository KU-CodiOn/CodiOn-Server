package com.example.codionbe.domain.coordination.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
@Schema(description = "코디 수정 요청")
public class CoordinationUpdateRequest {

    @Schema(description = "코디 날짜", example = "2024-05-12")
    private LocalDate date;

    @Schema(description = "수정할 옷 ID 목록", example = "[1, 4, 5]")
    private List<Long> clothesIds;
}
