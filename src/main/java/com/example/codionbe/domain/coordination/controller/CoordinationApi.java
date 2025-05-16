package com.example.codionbe.domain.coordination.controller;

import com.example.codionbe.domain.coordination.dto.request.CreateCoordinationRequest;
import com.example.codionbe.domain.coordination.dto.request.CoordinationUpdateRequest;
import com.example.codionbe.domain.coordination.dto.response.CoordinationDetailResponse;
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
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Tag(name = "코디 기록", description = "코디 기록 관련 API")
@RequestMapping("/coordination")
public interface CoordinationApi {

    @Operation(summary = "코디 등록 API", description = "선택한 날짜의 코디를 등록합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "코디 등록 성공",
                    content = @Content(schema = @Schema(implementation = SuccessResponse.class),
                            examples = @ExampleObject(value = """
                            {
                              "isSuccess": true,
                              "code": "COORD_001",
                              "message": "코디 등록에 성공했습니다.",
                              "data": null
                            }
                            """)))
    })
    @PostMapping
    ResponseEntity<SuccessResponse<Void>> createCoordination(
            @RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "코디 등록 요청") CreateCoordinationRequest request,
            @Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails userDetails);

    @Operation(summary = "코디 수정 API", description = "선택한 날짜의 코디 정보를 수정합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "코디 수정 성공",
                    content = @Content(schema = @Schema(implementation = SuccessResponse.class),
                            examples = @ExampleObject(value = """
                            {
                              "isSuccess": true,
                              "code": "COORD_002",
                              "message": "코디 수정에 성공했습니다.",
                              "data": null
                            }
                            """))),
            @ApiResponse(responseCode = "400", description = "유효하지 않은 옷 정보",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(value = """
                            {
                              "isSuccess": false,
                              "code": "COORD_101",
                              "message": "유효하지 않은 옷 정보입니다."
                            }
                            """)))
    })
    @PatchMapping
    ResponseEntity<SuccessResponse<Void>> updateCoordination(
            @RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "코디 수정 요청") CoordinationUpdateRequest request,
            @Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails userDetails);

    @Operation(summary = "코디 상세 조회 API", description = "날짜를 기준으로 코디 상세 정보를 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "코디 상세 조회 성공",
                    content = @Content(schema = @Schema(implementation = SuccessResponse.class),
                            examples = @ExampleObject(value = """
                            {
                              "isSuccess": true,
                              "code": "COORD_003",
                              "message": "코디 상세 조회에 성공했습니다.",
                              "data": {
                                "coordinationId": 1,
                                "date": "2024-05-11",
                                "clothes": [],
                                "comment": null
                              }
                            }
                            """))),
            @ApiResponse(responseCode = "404", description = "코디를 찾을 수 없음",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(value = """
                            {
                              "isSuccess": false,
                              "code": "COORD_102",
                              "message": "해당 날짜의 코디를 찾을 수 없습니다."
                            }
                            """)))
    })
    @GetMapping
    ResponseEntity<SuccessResponse<CoordinationDetailResponse>> getCoordination(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails userDetails);

    @Operation(summary = "코디 삭제 API", description = "해당 날짜의 코디를 삭제합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "코디 삭제 성공",
                    content = @Content(schema = @Schema(implementation = SuccessResponse.class),
                            examples = @ExampleObject(value = """
                            {
                              "isSuccess": true,
                              "code": "COORD_004",
                              "message": "코디 삭제에 성공했습니다.",
                              "data": null
                            }
                            """))),
            @ApiResponse(responseCode = "404", description = "코디를 찾을 수 없음",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(value = """
                            {
                              "isSuccess": false,
                              "code": "COORD_102",
                              "message": "해당 날짜의 코디를 찾을 수 없습니다."
                            }
                            """)))
    })
    @DeleteMapping
    ResponseEntity<SuccessResponse<Void>> deleteCoordination(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails userDetails);

}
