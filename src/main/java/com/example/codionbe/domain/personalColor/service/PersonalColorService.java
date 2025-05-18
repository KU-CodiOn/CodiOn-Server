package com.example.codionbe.domain.personalColor.service;

import com.example.codionbe.domain.personalColor.dto.PersonalColorResponse;
import com.example.codionbe.global.s3.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class PersonalColorService {

    private final S3Uploader s3Uploader;

    @Transactional
    public PersonalColorResponse analyzePersonalColor(MultipartFile image) throws IOException {

        String imageUrl = s3Uploader.upload(image, "personalColor");

        // 퍼스널컬러 분석하기

        String personalColor = "여름 쿨";

        return new PersonalColorResponse(personalColor);
    }
}
