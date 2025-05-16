package com.example.codionbe.domain.closet.service;

import com.example.codionbe.domain.closet.dto.response.ImageAnalysisResponse;
import com.example.codionbe.domain.closet.entity.Category;
import com.example.codionbe.domain.closet.entity.PersonalColor;
import com.example.codionbe.global.s3.S3Uploader;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClosetServiceTest {

    @Mock
    private S3Uploader s3Uploader;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private ClosetService closetService;

    private MockMultipartFile mockImage;
    private ObjectNode mockResultNode;
    private ObjectNode mockAnalysisNode;
    private String mockImageUrl;

    @BeforeEach
    void setUp() {
        // 테스트용 이미지 파일 생성
        mockImage = new MockMultipartFile(
                "image",
                "test_image.jpg",
                "image/jpeg",
                "test image content".getBytes()
        );

        // S3 업로드 후 반환될 가상의 URL
        mockImageUrl = "https://s3.test.com/bucket/test_image.jpg";

        // 파이썬 서버 응답 JSON 구조 생성
        ObjectMapper realObjectMapper = new ObjectMapper();
        mockAnalysisNode = realObjectMapper.createObjectNode();
        mockAnalysisNode.put("category", "TOP");
        mockAnalysisNode.put("personalColor", "SUMMER");
        mockAnalysisNode.put("color", "Light Blue");

        mockResultNode = realObjectMapper.createObjectNode();
        mockResultNode.put("result", mockAnalysisNode.toString());
    }

    @Test
    @DisplayName("이미지 업로드 및 분석 성공 테스트")
    void uploadAndAnalyzeImageSuccess() throws IOException {
        // Given
        when(s3Uploader.upload(any(), anyString())).thenReturn(mockImageUrl);
        when(restTemplate.postForObject(anyString(), any(HttpEntity.class), eq(String.class)))
                .thenReturn(mockResultNode.toString());
        when(objectMapper.readTree(mockResultNode.toString())).thenReturn(mockResultNode);
        when(objectMapper.readTree(mockAnalysisNode.toString())).thenReturn(mockAnalysisNode);

        // mockAnalysisNode의 get 호출에 대한 응답 설정
        JsonNode categoryNode = mockAnalysisNode.get("category");
        JsonNode personalColorNode = mockAnalysisNode.get("personalColor");
        JsonNode colorNode = mockAnalysisNode.get("color");

        when(mockResultNode.get("result")).thenReturn(mockAnalysisNode);
        when(mockAnalysisNode.get("category")).thenReturn(categoryNode);
        when(mockAnalysisNode.get("personalColor")).thenReturn(personalColorNode);
        when(mockAnalysisNode.get("color")).thenReturn(colorNode);
        when(categoryNode.asText()).thenReturn("TOP");
        when(personalColorNode.asText()).thenReturn("SUMMER");
        when(colorNode.asText()).thenReturn("Light Blue");

        // When
        ImageAnalysisResponse response = closetService.uploadAndAnalyzeImage(mockImage);

        // Then
        assertThat(response).isNotNull();
        assertThat(response.getImageUrl()).isEqualTo(mockImageUrl);
        assertThat(response.getCategory()).isEqualTo(Category.TOP);
        assertThat(response.getPersonalColor()).isEqualTo(PersonalColor.SUMMER);
        assertThat(response.getColor()).isEqualTo("Light Blue");
    }
} 