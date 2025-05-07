package com.example.codionbe.domain.coordination.controller;

import com.example.codionbe.domain.coordination.dto.CreateCoordinationRequest;
import com.example.codionbe.global.auth.CustomUserDetails;
import com.example.codionbe.global.common.success.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "코디 기록", description = "코디 기록 관련 API")
public interface CoordinationApi {

    @Operation(summary = "코디 등록 API")
    @ApiResponse(responseCode = "200", description = "코디 등록 성공")
    @PostMapping("")
    ResponseEntity<SuccessResponse<Void>> createCoordination(
            @RequestBody CreateCoordinationRequest request,
            @Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails userDetails);
}
