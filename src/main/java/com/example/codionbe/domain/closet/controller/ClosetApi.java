package com.example.codionbe.domain.closet.controller;

import com.example.codionbe.domain.closet.dto.request.RegisterClothesRequest;
import com.example.codionbe.domain.closet.dto.request.ClothesFilterRequest;
import com.example.codionbe.domain.closet.dto.request.UpdateClothesRequest;
import com.example.codionbe.domain.closet.dto.response.ClothesResponse;
import com.example.codionbe.global.auth.CustomUserDetails;
import com.example.codionbe.global.common.success.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Tag(name = "MY 옷장", description = "MY 옷장 관련 API")
public interface ClosetApi {
    @Operation(summary = "옷 등록 API")
    @ApiResponse(responseCode = "200", description = "옷 등록 성공")
    @PostMapping(value = "/closet", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<SuccessResponse<Void>> registerClothes(
            @RequestPart("image") MultipartFile image,
            @RequestPart("request") RegisterClothesRequest request,
            @Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails userDetails
    ) throws IOException;

    @Operation(summary = "옷 전체 조회 API")
    @ApiResponse(responseCode = "200", description = "MY 옷장 전체 조회 성공")
    @GetMapping("/closet")
    ResponseEntity<SuccessResponse<List<ClothesResponse>>> getClothesList(
            @Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails userDetails,
            ClothesFilterRequest filterRequest
    );

    @Operation(summary = "옷 수정 API", description = "기존 옷 정보를 수정합니다.")
    @ApiResponse(responseCode = "200", description = "옷 수정 성공")
    @PatchMapping("/closet/{clothesId}")
    ResponseEntity<SuccessResponse<Void>> updateClothes(
            @PathVariable("clothesId") Long clothesId,
            @RequestPart(value = "image", required = false) MultipartFile image,
            @RequestPart("request") UpdateClothesRequest request,
            @Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails userDetails
    ) throws IOException;
}
