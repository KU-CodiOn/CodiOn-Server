package com.example.codionbe.domain.comment.controller;

import com.example.codionbe.domain.comment.dto.request.CommentCreateRequest;
import com.example.codionbe.domain.comment.dto.request.CommentUpdateRequest;
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

@Tag(name = "코디 기록 코멘트", description = "코디 기록 코멘트 관련 API")
@RequestMapping("/coordination/comment")
public interface CommentApi {

    @Operation(summary = "코멘트 생성 API", description = "코디에 코멘트를 등록합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "코멘트 등록 성공",
                    content = @Content(schema = @Schema(implementation = SuccessResponse.class),
                            examples = @ExampleObject(value = """
                            {
                              "code": "COMMENT_001",
                              "message": "코멘트 등록에 성공했습니다.",
                              "data": null
                            }
                            """))),
            @ApiResponse(responseCode = "400", description = "이미 코멘트가 존재함",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(value = """
                            {
                              "code": "COMMENT_102",
                              "message": "이미 코멘트가 등록되어 있습니다."
                            }
                            """))),
            @ApiResponse(responseCode = "404", description = "코디 없음",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(value = """
                            {
                              "code": "COMMENT_103",
                              "message": "해당 날짜의 코디가 존재하지 않습니다."
                            }
                            """)))
    })
    @PostMapping
    ResponseEntity<SuccessResponse<Void>> createComment(
            @RequestBody CommentCreateRequest request,
            @Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails userDetails);

    @Operation(summary = "코멘트 수정 API", description = "등록된 코멘트를 수정합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "코멘트 수정 성공",
                    content = @Content(schema = @Schema(implementation = SuccessResponse.class),
                            examples = @ExampleObject(value = """
                            {
                              "code": "COMMENT_002",
                              "message": "코멘트 수정에 성공했습니다.",
                              "data": null
                            }
                            """))),
            @ApiResponse(responseCode = "404", description = "코디 또는 코멘트 없음",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(value = """
                            {
                              "code": "COMMENT_103",
                              "message": "해당 날짜의 코디가 존재하지 않습니다."
                            }
                            """)))
    })
    @PatchMapping
    ResponseEntity<SuccessResponse<Void>> updateComment(
            @RequestBody CommentUpdateRequest request,
            @Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails userDetails);

    @Operation(summary = "코멘트 삭제 API", description = "코디에 등록된 코멘트를 삭제합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "코멘트 삭제 성공",
                    content = @Content(schema = @Schema(implementation = SuccessResponse.class),
                            examples = @ExampleObject(value = """
                            {
                              "code": "COMMENT_003",
                              "message": "코멘트 삭제에 성공했습니다.",
                              "data": null
                            }
                            """))),
            @ApiResponse(responseCode = "404", description = "코디 또는 코멘트 없음",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class),
                            examples = @ExampleObject(value = """
                            {
                              "code": "COMMENT_103",
                              "message": "해당 날짜의 코디가 존재하지 않습니다."
                            }
                            """)))
    })
    @DeleteMapping
    ResponseEntity<SuccessResponse<Void>> deleteComment(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails userDetails);

}
