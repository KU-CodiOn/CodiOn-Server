package com.example.codionbe.domain.personalColor.service;

import com.example.codionbe.domain.personalColor.dto.PersonalColorResponse;
import com.example.codionbe.global.s3.S3Uploader;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PersonalColorService {

    private final S3Uploader s3Uploader;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Transactional
    public PersonalColorResponse analyzePersonalColor(MultipartFile image) throws IOException {
        // 1. S3에 이미지 업로드
        String imageUrl = s3Uploader.upload(image, "personalColor");
        
        // 2. 파이썬 서버에 이미지 URL 전송하여 분석 요청
        String analysisUrl = "http://43.203.196.176/analyze/fashion/url";
        
        // 요청 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // 요청 바디 설정
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("image_url", imageUrl);

        // HTTP 요청 생성
        HttpEntity<Map<String, String>> request = new HttpEntity<>(requestBody, headers);
        
        // 요청 전송 및 응답 수신
        String responseBody = restTemplate.postForObject(analysisUrl, request, String.class);
        
        // 응답 파싱
        JsonNode root = objectMapper.readTree(responseBody);
        String result = root.get("result").asText();
        JsonNode analysisResult = objectMapper.readTree(result);
        
        // 응답 데이터에서 퍼스널컬러 추출
        String personalColorEnglish = analysisResult.get("퍼스널컬러").asText().toLowerCase();
        
        // 영문 계절명을 한글 퍼스널컬러로 매핑
        String personalColorKorean = mapToKoreanPersonalColor(personalColorEnglish);
        
        // 결과 반환 - PersonalColorResponse 생성자는 personalColor를 받음
        return new PersonalColorResponse(personalColorKorean);
    }
    
    /**
     * 영문 계절명을 한글 퍼스널컬러로 매핑
     */
    private String mapToKoreanPersonalColor(String englishName) {
        return switch (englishName) {
            case "spring" -> "봄 웜";
            case "summer" -> "여름 쿨";
            case "autumn" -> "가을 웜";
            case "winter" -> "겨울 쿨";
            default -> "분석 불가"; // 매핑할 수 없는 경우 기본값
        };
    }
}
