package com.example.codionbe.domain.personalColor.controller;

import com.example.codionbe.domain.personalColor.dto.PersonalColorResponse;
import com.example.codionbe.global.common.success.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Tag(name = "퍼스널 컬러", description = "퍼스널 컬러 관련 API")
@RequestMapping("/personal-color")
public interface PersonalColorApi {

    @Operation(summary = "퍼스널컬러 분석 API", description = "카메라를 통한 퍼스널컬러 분석합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "퍼스널컬러 분석 성공",
                    content = @Content(schema = @Schema(implementation = SuccessResponse.class),
                            examples = @ExampleObject(value = """
                            {
                              "isSuccess": true,
                              "code": "PERSONAL_COLOR_001",
                              "message": "퍼스널컬러 분석에 성공했습니다.",
                              "data": {
                                "personalColor": "봄 웜"
                              }
                            }
                            """)))
    })
    @PostMapping("/analyze")
    ResponseEntity<SuccessResponse<PersonalColorResponse>> analyzePersonalColor(
            @RequestPart("image") MultipartFile image
    ) throws IOException;
}
