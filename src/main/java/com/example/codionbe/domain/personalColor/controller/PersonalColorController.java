package com.example.codionbe.domain.personalColor.controller;

import com.example.codionbe.domain.personalColor.dto.PersonalColorResponse;
import com.example.codionbe.domain.personalColor.exception.PersonalColorSuccessCode;
import com.example.codionbe.domain.personalColor.service.PersonalColorService;
import com.example.codionbe.global.common.success.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class PersonalColorController implements PersonalColorApi {

    private final PersonalColorService personalColorService;

    @Override
    public ResponseEntity<SuccessResponse<PersonalColorResponse>> analyzePersonalColor(
            MultipartFile image) throws IOException {

        PersonalColorResponse response = personalColorService.analyzePersonalColor(image);
        return ResponseEntity.ok(new SuccessResponse<>(PersonalColorSuccessCode.PERSONAL_COLOR_ANALYSIS_SUCCESS, response));
    }
}
