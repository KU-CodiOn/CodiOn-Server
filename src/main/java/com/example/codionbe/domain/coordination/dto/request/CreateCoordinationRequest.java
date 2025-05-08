package com.example.codionbe.domain.coordination.dto.request;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class CreateCoordinationRequest {
    private LocalDate date;
    private List<Long> clothesIds;
}
