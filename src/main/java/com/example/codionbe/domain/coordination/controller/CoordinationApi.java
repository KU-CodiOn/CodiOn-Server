package com.example.codionbe.domain.coordination.controller;

import com.example.codionbe.domain.coordination.dto.CreateCoordinationRequest;
import com.example.codionbe.domain.coordination.dto.request.CoordinationUpdateRequest;
import com.example.codionbe.domain.coordination.dto.response.CoordinationDetailResponse;
import com.example.codionbe.global.auth.CustomUserDetails;
import com.example.codionbe.global.common.success.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Tag(name = "코디 기록", description = "코디 기록 관련 API")
public interface CoordinationApi {

    @Operation(summary = "코디 등록 API", description = "선택한 날짜의 코디를 등록합니다.")
    @ApiResponse(responseCode = "200", description = "코디 등록 성공")
    @PostMapping
    ResponseEntity<SuccessResponse<Void>> createCoordination(
            @RequestBody CreateCoordinationRequest request,
            @Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails userDetails);

    @Operation(summary = "코디 수정 API", description = "선택한 날짜의 코디 정보를 수정합니다.")
    @ApiResponse(responseCode = "200", description = "코디 수정 성공")
    @PatchMapping
    ResponseEntity<SuccessResponse<Void>> updateCoordination(
            @RequestBody CoordinationUpdateRequest request,
            @Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails userDetails);

    @Operation(summary = "코디 상세 조회 API", description = "날짜를 기준으로 코디 상세 정보를 조회합니다.")
    @ApiResponse(responseCode = "200", description = "코디 상세 조회 성공")
    @GetMapping
    ResponseEntity<SuccessResponse<CoordinationDetailResponse>> getCoordination(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails userDetails);

    @Operation(summary = "코디 삭제 API", description = "해당 날짜의 코디를 삭제합니다.")
    @ApiResponse(responseCode = "200", description = "코디 삭제 성공")
    @DeleteMapping
    ResponseEntity<SuccessResponse<Void>> deleteCoordination(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails userDetails);

}
