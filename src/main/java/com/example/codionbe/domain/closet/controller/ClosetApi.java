package com.example.codionbe.domain.closet.controller;

import com.example.codionbe.domain.closet.dto.request.RegisterClothesRequest;
import com.example.codionbe.domain.closet.dto.request.ClothesFilterRequest;
import com.example.codionbe.domain.closet.dto.request.UpdateClothesRequest;
import com.example.codionbe.domain.closet.dto.response.ClothesResponse;
import com.example.codionbe.domain.closet.dto.response.FavoriteToggleResponse;
import com.example.codionbe.domain.closet.dto.response.ImageAnalysisResponse;
import com.example.codionbe.global.auth.CustomUserDetails;
import com.example.codionbe.global.common.exception.ErrorResponse;
import com.example.codionbe.global.common.success.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Tag(name = "MY 옷장", description = "MY 옷장 관련 API")
@RequestMapping("/closet")
public interface ClosetApi {
    @Operation(summary = "이미지 업로드 및 분석", description = "이미지를 업로드하고 AI로 분석합니다.")
    @ApiResponse(responseCode = "200", description = "이미지 분석 성공")
    @PostMapping(value = "/image")
    ResponseEntity<SuccessResponse<ImageAnalysisResponse>> uploadAndAnalyzeImage(
            @Parameter(description = "분석할 이미지 파일") @RequestParam("image") MultipartFile image,
            @Parameter(hidden = true) CustomUserDetails userDetails
    ) throws IOException;

    @Operation(summary = "옷 등록 API", description = "새로운 옷의 정보를 등록합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "옷 등록 성공",
                    content = @Content(schema = @Schema(implementation = SuccessResponse.class),
                            examples = @ExampleObject(value = """
                            {
                              "isSuccess": true,
                              "code": "CLOSET_001",
                              "message": "옷 등록에 성공했습니다.",
                              "data": null
                            }
                            """)))
    })
    @PostMapping
    ResponseEntity<SuccessResponse<Void>> registerClothes(
            @RequestBody RegisterClothesRequest request,
            @Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails userDetails
    ) throws IOException;

    @Operation(summary = "옷 전체 조회 API", description = "전체 옷의 정보를 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "MY 옷장 목록 조회 성공",
                    content = @Content(schema = @Schema(implementation = SuccessResponse.class),
                            examples = @ExampleObject(value = """
                            {
                              "isSuccess": true,
                              "code": "CLOSET_002",
                              "message": "MY 옷장 목록 조회에 성공했습니다.",
                              "data": [ ]
                            }
                            """)))
    })
    @GetMapping
    ResponseEntity<SuccessResponse<List<ClothesResponse>>> getClothesList(
            @Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails userDetails,
            ClothesFilterRequest filterRequest
    );

    @Operation(summary = "옷 수정 API", description = "기존 옷 정보를 수정합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "옷 수정 성공",
                    content = @Content(schema = @Schema(implementation = SuccessResponse.class),
                            examples = @ExampleObject(value = """
                            {
                              "isSuccess": true,
                              "code": "CLOSET_003",
                              "message": "옷 수정에 성공했습니다.",
                              "data": null
                            }
                            """))),
            @ApiResponse(responseCode = "404", description = "옷 없음",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(value = """
                            {
                              "isSuccess": false,
                              "code": "CLOSET_101",
                              "message": "해당 옷을 찾을 수 없습니다."
                            }
                            """))),
            @ApiResponse(responseCode = "403", description = "권한 없음",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(value = """
                            {
                              "isSuccess": false,
                              "code": "CLOSET_102",
                              "message": "해당 옷에 대한 권한이 없습니다."
                            }
                            """)))
    })
    @PatchMapping("/{clothesId}")
    ResponseEntity<SuccessResponse<Void>> updateClothes(
            @PathVariable("clothesId") Long clothesId,
            @RequestBody UpdateClothesRequest request,
            @Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails userDetails
    ) throws IOException;

    @Operation(summary = "옷 삭제 API", description = "해당 옷을 MY 옷장에서 삭제합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "옷 삭제 성공",
                    content = @Content(schema = @Schema(implementation = SuccessResponse.class),
                            examples = @ExampleObject(value = """
                            {
                              "isSuccess": true,
                              "code": "CLOSET_004",
                              "message": "옷 삭제에 성공했습니다.",
                              "data": null
                            }
                            """)))
    })
    @DeleteMapping("/{clothesId}")
    ResponseEntity<SuccessResponse<Void>> deleteClothes(
            @PathVariable("clothesId") Long clothesId,
            @Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails userDetails
    );

    @Operation(summary = "옷 상세 조회 API", description = "옷 ID를 통해 옷의 상세 정보를 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "옷 상세 조회 성공",
                    content = @Content(schema = @Schema(implementation = SuccessResponse.class),
                            examples = @ExampleObject(value = """
                            {
                              "isSuccess": true,
                              "code": "CLOSET_006",
                              "message": "옷 상세 조회에 성공했습니다.",
                              "data": {
                                "clothesId": 1,
                                "name": "화이트 셔츠",
                                "imageUrl": "https://s3.amazonaws.com/bucket/image.jpg",
                                "category": "TOP",
                                "personalColor": "SUMMER",
                                "color": "WHITE",
                                "situationKeywords": ["데이트", "면접"],
                                "wearCount": 0,
                                "favorite": false,
                                "wearableWhenRainy": true
                              }
                            }
                            """)))
    })
    @GetMapping("/{clothesId}")
    ResponseEntity<SuccessResponse<ClothesResponse>> getClothesDetail(
            @PathVariable("clothesId") Long clothesId,
            @Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails userDetails
    );

    @Operation(summary = "즐겨찾기 토글 API", description = "옷의 즐겨찾기 상태를 바꿉니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "즐겨찾기 상태 변경 성공",
                    content = @Content(schema = @Schema(implementation = SuccessResponse.class),
                            examples = @ExampleObject(value = """
                            {
                              "isSuccess": true,
                              "code": "CLOSET_005",
                              "message": "즐겨찾기 상태가 변경되었습니다.",
                              "data": {
                                "clothesId": 1,
                                "favorite": true
                              }
                            }
                            """)))
    })
    @PatchMapping("/{clothesId}/favorite")
    ResponseEntity<SuccessResponse<FavoriteToggleResponse>> toggleFavorite(
            @PathVariable("clothesId") Long clothesId,
            @Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails userDetails
    );
}
