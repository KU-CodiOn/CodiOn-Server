package com.example.codionbe.domain.closet.service;

import com.example.codionbe.domain.closet.dto.request.ClothesFilterRequest;
import com.example.codionbe.domain.closet.dto.request.UpdateClothesRequest;
import com.example.codionbe.domain.closet.dto.response.ClothesResponse;
import com.example.codionbe.domain.closet.dto.response.FavoriteToggleResponse;
import com.example.codionbe.domain.closet.dto.response.ImageAnalysisResponse;
import com.example.codionbe.domain.closet.entity.Category;
import com.example.codionbe.domain.closet.entity.Clothes;
import com.example.codionbe.domain.closet.dto.request.RegisterClothesRequest;
import com.example.codionbe.domain.closet.entity.PersonalColor;
import com.example.codionbe.domain.closet.exception.ClosetErrorCode;
import com.example.codionbe.domain.closet.repository.ClothesRepository;
import com.example.codionbe.global.common.exception.CustomException;
import com.example.codionbe.global.s3.S3Uploader;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClosetService {

    private final ClothesRepository clothesRepository;
    private final S3Uploader s3Uploader;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Value("${ai.server.url:http://43.203.196.176}")
    private String aiServerUrl;

    @Transactional
    public ImageAnalysisResponse uploadAndAnalyzeImage(MultipartFile image) throws IOException {
        // 1. S3에 이미지 업로드
        String imageUrl = s3Uploader.upload(image, "closet");

        // 2. 파이썬 서버에 이미지 URL 전송하여 분석 요청
        String analysisUrl = aiServerUrl + "/analyze/fashion/url";

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
        
        // 응답 데이터 추출
        String categoryStr = analysisResult.get("category").asText();
        String personalColorStr = analysisResult.get("personalColor").asText();
        String color = analysisResult.get("color").asText();
        
        // Enum 변환
        Category category;
        try {
            category = Category.valueOf(categoryStr);
        } catch (IllegalArgumentException e) {
            category = Category.TOP; // 기본값 설정
        }
        
        PersonalColor personalColor;
        try {
            personalColor = PersonalColor.valueOf(personalColorStr);
        } catch (IllegalArgumentException e) {
            personalColor = PersonalColor.SPRING; // 기본값 설정
        }
        
        // 결과 반환
        return ImageAnalysisResponse.builder()
                .imageUrl(imageUrl)
                .category(category)
                .personalColor(personalColor)
                .color(color)
                .build();
    }

    @Transactional
    public void registerClothes(Long userId, MultipartFile image, RegisterClothesRequest request) throws IOException {
        String imageUrl = s3Uploader.upload(image, "closet");

        Clothes clothes = Clothes.builder()
                .userId(userId)
                .name(request.getName())
                .category(request.getCategory())
                .personalColor(request.getPersonalColor())
                .color(request.getColor())
                .suitableForRain(request.isSuitableForRain())
                .situationKeywords(String.join(",", request.getSituationKeywords()))
                .imageUrl(imageUrl)
                .isFavorite(false)
                .wearCount(0)
                .isDeleted(false)
                .build();

        clothesRepository.save(clothes);
    }

    @Transactional(readOnly = true)
    public List<ClothesResponse> getClothesList(Long userId, ClothesFilterRequest filterRequest) {
        List<Clothes> filtered = clothesRepository.findByUserIdAndFilter(userId, filterRequest);

        return filtered.stream().map(ClothesResponse::from).collect(Collectors.toList());
    }

    @Transactional
    public void updateClothes(Long userId, Long clothesId, MultipartFile image, UpdateClothesRequest request) throws IOException {
        Clothes clothes = clothesRepository.findById(clothesId)
                .orElseThrow(() -> new CustomException(ClosetErrorCode.CLOTHES_NOT_FOUND));

        // 사용자의 옷이 맞는지 검증
        if (!clothes.getUserId().equals(userId)) {
            throw new CustomException(ClosetErrorCode.NO_AUTHORITY);
        }

        // 이미지가 있으면 S3에 업로드 후 기존 이미지 수정
        if (image != null && !image.isEmpty()) {
            String imageUrl = s3Uploader.upload(image, "closet");
            clothes.updateImageUrl(imageUrl);
        }

        // 나머지 정보 수정
        clothes.updateInfo(
                request.getName(),
                request.getCategory(),
                request.getPersonalColor(),
                request.getColor(),
                request.getSuitableForRain(),
                request.getSituationKeywords()
        );
    }

    @Transactional
    public void deleteClothes(Long userId, Long clothesId) {
        Clothes clothes = clothesRepository.findById(clothesId)
                .orElseThrow(() -> new CustomException(ClosetErrorCode.CLOTHES_NOT_FOUND));

        if (!clothes.getUserId().equals(userId)) {
            throw new CustomException(ClosetErrorCode.NO_AUTHORITY);
        }

        clothes.delete(); // isDeleted = true
    }

    @Transactional(readOnly = true)
    public ClothesResponse getClothesDetail(Long userId, Long clothesId) {
        Clothes clothes = clothesRepository.findById(clothesId)
                .orElseThrow(() -> new CustomException(ClosetErrorCode.CLOTHES_NOT_FOUND));

        if (!clothes.getUserId().equals(userId)) {
            throw new CustomException(ClosetErrorCode.NO_AUTHORITY);
        }

        if (clothes.isDeleted()) {
            throw new CustomException(ClosetErrorCode.CLOTHES_NOT_FOUND);
        }

        return ClothesResponse.from(clothes);
    }

    @Transactional
    public FavoriteToggleResponse toggleFavorite(Long userId, Long clothesId) {
        Clothes clothes = clothesRepository.findById(clothesId)
                .orElseThrow(() -> new CustomException(ClosetErrorCode.CLOTHES_NOT_FOUND));

        if (!clothes.getUserId().equals(userId)) {
            throw new CustomException(ClosetErrorCode.NO_AUTHORITY);
        }

        clothes.toggleFavorite(); // true → false, false → true

        return new FavoriteToggleResponse(clothes.getId(), clothes.isFavorite());
    }
}
