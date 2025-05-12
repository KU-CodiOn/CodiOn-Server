package com.example.codionbe.domain.coordination.dto.response;

import com.example.codionbe.domain.comment.dto.response.CommentResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
@Schema(description = "코디 상세 조회 응답")
public class CoordinationDetailResponse {

    @Schema(description = "코디 ID", example = "1")
    private Long coordinationId;

    @Schema(description = "코디 날짜", example = "2024-05-12")
    private LocalDate date;

    @Schema(description = "코디에 포함된 옷 정보 목록")
    private List<ClothesSimpleResponse> clothes;

    @Schema(description = "코디에 남긴 코멘트", nullable = true)
    private CommentResponse comment;
}
