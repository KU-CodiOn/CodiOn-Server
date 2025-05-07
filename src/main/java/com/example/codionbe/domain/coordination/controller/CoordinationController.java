package com.example.codionbe.domain.coordination.controller;

import com.example.codionbe.domain.coordination.dto.CreateCoordinationRequest;
import com.example.codionbe.domain.coordination.exception.CoordinationSuccessCode;
import com.example.codionbe.domain.coordination.service.CoordinationService;
import com.example.codionbe.global.auth.CustomUserDetails;
import com.example.codionbe.global.common.success.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
