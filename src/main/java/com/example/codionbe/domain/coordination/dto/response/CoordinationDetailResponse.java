package com.example.codionbe.domain.coordination.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
public class CoordinationDetailResponse {
    private Long coordinationId;
    private LocalDate date;
    private List<ClothesSimpleResponse> clothes;
}
