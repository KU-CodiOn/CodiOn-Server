package com.example.codionbe.domain.personalColor.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "퍼스널컬러 분석 응답")
public class PersonalColorResponse {

    @Schema(description = "퍼스널컬러", example = "여름 쿨")
    private String personalColor;
}
