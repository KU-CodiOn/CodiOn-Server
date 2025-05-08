package com.example.codionbe.domain.coordination.dto.request;

import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
public class CoordinationUpdateRequest {
    private LocalDate date;
    private List<Long> clothesIds;
}
