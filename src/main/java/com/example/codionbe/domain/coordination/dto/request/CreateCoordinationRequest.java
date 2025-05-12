package com.example.codionbe.domain.coordination.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
@Schema(description = "코디 등록 요청")
public class CreateCoordinationRequest {

    @Schema(description = "코디 날짜", example = "2025-05-12")
    private LocalDate date;

    @Schema(description = "코디에 포함된 옷 ID 목록", example = "[1, 2, 3]")
    private List<Long> clothesIds;
}
