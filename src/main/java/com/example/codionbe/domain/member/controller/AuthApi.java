package com.example.codionbe.domain.member.controller;

import com.example.codionbe.domain.member.dto.request.KakaoLoginRequest;
import com.example.codionbe.domain.member.dto.request.LoginRequest;
import com.example.codionbe.domain.member.dto.request.SignUpRequest;
import com.example.codionbe.domain.member.dto.request.TokenRefreshRequest;
import com.example.codionbe.domain.member.dto.response.LoginResponse;
import com.example.codionbe.domain.member.dto.response.SignUpResponse;
import com.example.codionbe.domain.member.dto.response.TokenRefreshResponse;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "회원", description = "회원 관련 API")
public interface AuthApi {

    @Operation(summary = "회원가입 API", description = "이메일, 비밀번호, 닉네임, 퍼스널컬러 정보를 입력받아 회원가입을 진행합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "회원가입 성공",
                    content = @Content(schema = @Schema(implementation = SuccessResponse.class),
                            examples = @ExampleObject(value = """
                            {
                              "isSuccess": true,
                              "code": "AUTH_001",
                              "message": "회원가입이 성공적으로 완료되었습니다.",
                              "data": {
                                "userId": 1,
                                "email": "test@example.com",
                                "nickname": "홍길동"
                              }
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
            @ApiResponse(responseCode = "409", description = "이메일 중복",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(value = """
                            {
                              "isSuccess": false,
                              "code": "AUTH_101",
                              "message": "이미 사용 중인 이메일입니다."
                            }
                            """)))
    })
    @PostMapping("/signup")
    ResponseEntity<SuccessResponse<SignUpResponse>> signup(
            @RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "회원가입 요청") SignUpRequest request
    );

    @Operation(summary = "로그인 API", description = "이메일과 비밀번호로 로그인을 시도하고 토큰을 발급받습니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "로그인 성공",
                    content = @Content(schema = @Schema(implementation = SuccessResponse.class),
                            examples = @ExampleObject(value = """
                            {
                              "isSuccess": true,
                              "code": "AUTH_002",
                              "message": "로그인이 성공적으로 완료되었습니다.",
                              "data": {
                                "accessToken": "access-token-value",
                                "refreshToken": "refresh-token-value"
                              }
                            }
                            """))),
            @ApiResponse(responseCode = "401", description = "비밀번호 불일치",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(value = """
                            {
                              "isSuccess": false,
                              "code": "AUTH_104",
                              "message": "비밀번호가 일치하지 않습니다."
                            }
                            """))),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 사용자",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(value = """
                            {
                              "isSuccess": false,
                              "code": "AUTH_103",
                              "message": "사용자를 찾을 수 없습니다."
                            }
                            """)))
    })
    @PostMapping("/login")
    ResponseEntity<SuccessResponse<LoginResponse>> login(
            @RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "로그인 요청") LoginRequest request
    );

    @Operation(summary = "AccessToken 재발급 API", description = "RefreshToken을 기반으로 새로운 AccessToken을 발급합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "재발급 성공",
                    content = @Content(schema = @Schema(implementation = SuccessResponse.class),
                            examples = @ExampleObject(value = """
                            {
                              "isSuccess": true,
                              "code": "AUTH_003",
                              "message": "토큰이 성공적으로 재발급되었습니다.",
                              "data": {
                                "accessToken": "new-access-token-value"
                              }
                            }
                            """))),
            @ApiResponse(responseCode = "401", description = "유효하지 않은 토큰",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(value = """
                            {
                              "isSuccess": false,
                              "code": "AUTH_107",
                              "message": "유효하지 않은 JWT 토큰입니다."
                            }
                            """)))
    })
    @PostMapping("/refresh")
    ResponseEntity<SuccessResponse<TokenRefreshResponse>> refresh(
            @RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "RefreshToken 요청") TokenRefreshRequest request
    );

    @Operation(
            summary = "로그아웃 API",
            description = "AccessToken으로 인증된 사용자의 RefreshToken을 삭제하여 로그아웃 처리합니다.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "로그아웃 성공",
                    content = @Content(schema = @Schema(implementation = SuccessResponse.class),
                            examples = @ExampleObject(value = """
                            {
                              "isSuccess": true,
                              "code": "AUTH_004",
                              "message": "로그아웃 성공",
                              "data": null
                            }
                            """))),
            @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(value = """
                            {
                              "isSuccess": false,
                              "code": "AUTH_105",
                              "message": "인증 정보가 유효하지 않습니다."
                            }
                            """)))
    })
    @DeleteMapping("/logout")
    ResponseEntity<SuccessResponse<Void>> logout(
            @Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails userDetails
    );

    @Operation(summary = "카카오 로그인 API", description = "카카오 인가 코드를 이용한 로그인")
    @ApiResponse(responseCode = "200", description = "카카오 로그인 성공")
    @PostMapping("/kakao")
    ResponseEntity<SuccessResponse<LoginResponse>> kakaoLogin(@RequestBody KakaoLoginRequest request);

}

