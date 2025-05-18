package com.example.codionbe.domain.mypage.controller;

import com.example.codionbe.domain.mypage.dto.request.UpdatePasswordRequest;
import com.example.codionbe.domain.mypage.dto.request.UpdateProfileRequest;
import com.example.codionbe.domain.mypage.dto.response.MyPageResponse;
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
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "마이페이지", description = "마이페이지 관련 API")
@RequestMapping("/mypage")
public interface MyPageApi {

    @Operation(
            summary = "마이페이지 조회 API",
            description = "현재 로그인된 사용자의 마이페이지 정보를 반환합니다.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "회원 정보 조회 성공",
                    content = @Content(schema = @Schema(implementation = SuccessResponse.class),
                            examples = @ExampleObject(value = """
                            {
                              "isSuccess": true,
                              "code": "MYPAGE_001",
                              "message": "회원 정보 조회 성공",
                              "data": {
                                "email": "test@example.com",
                                "nickname": "홍길동",
                                "personalColor": "SPRING"
                              }
                            }
                            """))),
            @ApiResponse(responseCode = "401", description = "인증 정보가 유효하지 않습니다.",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(value = """
                            {
                              "isSuccess": false,
                              "code": "AUTH_105",
                              "message": "인증 정보가 유효하지 않습니다."
                            }
                            """)))
    })
    @GetMapping
    ResponseEntity<SuccessResponse<MyPageResponse>> getMyPage(
            @Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails userDetails
    );

    @Operation(
            summary = "프로필 수정 API",
            description = "닉네임과 퍼스널 컬러 정보를 수정합니다.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "회원 정보 수정 성공",
                    content = @Content(schema = @Schema(implementation = SuccessResponse.class),
                            examples = @ExampleObject(value = """
                            {
                              "isSuccess": true,
                              "code": "MYPAGE_002",
                              "message": "회원 정보 수정 성공",
                              "data": {
                                "email": "test@example.com",
                                "nickname": "변경된이름",
                                "personalColor": "SUMMER"
                              }
                            }
                            """))),
            @ApiResponse(responseCode = "401", description = "인증 정보가 유효하지 않습니다.",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(value = """
                            {
                              "isSuccess": false,
                              "code": "AUTH_105",
                              "message": "인증 정보가 유효하지 않습니다."
                            }
                            """)))
    })
    @PatchMapping
    ResponseEntity<SuccessResponse<MyPageResponse>> updateProfile(
            @RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "회원 프로필 수정 요청") UpdateProfileRequest request,
            @Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails userDetails
    );

    @Operation(
            summary = "비밀번호 변경 API",
            description = "기존 비밀번호 없이 새 비밀번호만 입력받아 변경합니다.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "비밀번호 변경 성공",
                    content = @Content(schema = @Schema(implementation = SuccessResponse.class),
                            examples = @ExampleObject(value = """
                            {
                              "isSuccess": true,
                              "code": "MYPAGE_003",
                              "message": "비밀번호 변경 성공",
                              "data": null
                            }
                            """))),
            @ApiResponse(responseCode = "400", description = "비밀번호 불일치",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(value = """
                            {
                              "isSuccess": false,
                              "code": "AUTH_102",
                              "message": "비밀번호와 비밀번호 확인이 일치하지 않습니다."
                            }
                            """))),
            @ApiResponse(responseCode = "401", description = "인증 정보가 유효하지 않습니다.",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(value = """
                            {
                              "isSuccess": false,
                              "code": "AUTH_105",
                              "message": "인증 정보가 유효하지 않습니다."
                            }
                            """)))
    })
    @PatchMapping("/password")
    ResponseEntity<SuccessResponse<Void>> updatePassword(
            @RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "비밀번호 변경 요청") UpdatePasswordRequest request,
            @Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails userDetails
    );

    @Operation(
            summary = "회원 탈퇴 API",
            description = "회원 탈퇴를 수행하며 실제 삭제는 하지 않고 논리적으로 탈퇴 처리합니다.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "회원 탈퇴 성공",
                    content = @Content(schema = @Schema(implementation = SuccessResponse.class),
                            examples = @ExampleObject(value = """
                            {
                              "isSuccess": true,
                              "code": "MYPAGE_004",
                              "message": "회원 탈퇴 성공",
                              "data": null
                            }
                            """))),
            @ApiResponse(responseCode = "401", description = "인증 정보가 유효하지 않습니다.",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(value = """
                            {
                              "isSuccess": false,
                              "code": "AUTH_105",
                              "message": "인증 정보가 유효하지 않습니다."
                            }
                            """)))
    })
    @DeleteMapping
    ResponseEntity<SuccessResponse<Void>> deleteUser(
            @Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails userDetails
    );
}
