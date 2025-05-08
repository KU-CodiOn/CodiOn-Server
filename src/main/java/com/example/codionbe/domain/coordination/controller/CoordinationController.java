package com.example.codionbe.domain.coordination.controller;

import com.example.codionbe.domain.coordination.dto.request.CreateCoordinationRequest;
import com.example.codionbe.domain.coordination.dto.request.CoordinationUpdateRequest;
import com.example.codionbe.domain.coordination.dto.response.CoordinationDetailResponse;
import com.example.codionbe.domain.coordination.exception.CoordinationSuccessCode;
import com.example.codionbe.domain.coordination.service.CoordinationService;
import com.example.codionbe.global.auth.CustomUserDetails;
import com.example.codionbe.global.common.success.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/coordination")
public class CoordinationController implements CoordinationApi {

    private final CoordinationService coordinationService;

    @Override
    public ResponseEntity<SuccessResponse<Void>> createCoordination(
            CreateCoordinationRequest request,
            @AuthenticationPrincipal CustomUserDetails userDetails) {

        coordinationService.createCoordination(userDetails.getUser().getId(), request);
        return ResponseEntity.ok(new SuccessResponse<>(CoordinationSuccessCode.COORDINATION_CREATE_SUCCESS, null));
    }

    @Override
    public ResponseEntity<SuccessResponse<Void>> updateCoordination(
            CoordinationUpdateRequest request,
            CustomUserDetails userDetails) {

        coordinationService.updateCoordination(userDetails.getUser().getId(), request);
        return ResponseEntity.ok(new SuccessResponse<>(CoordinationSuccessCode.COORDINATION_UPDATE_SUCCESS, null));
    }

    @Override
    public ResponseEntity<SuccessResponse<CoordinationDetailResponse>> getCoordination(
            LocalDate date, CustomUserDetails userDetails) {

        CoordinationDetailResponse response = coordinationService.getCoordination(userDetails.getUser().getId(), date);
        return ResponseEntity.ok(new SuccessResponse<>(CoordinationSuccessCode.COORDINATION_GET_SUCCESS, response));
    }

    @Override
    public ResponseEntity<SuccessResponse<Void>> deleteCoordination(
            LocalDate date, CustomUserDetails userDetails) {

        coordinationService.deleteCoordination(userDetails.getUser().getId(), date);
        return ResponseEntity.ok(new SuccessResponse<>(CoordinationSuccessCode.COORDINATION_DELETE_SUCCESS, null));
    }

}
