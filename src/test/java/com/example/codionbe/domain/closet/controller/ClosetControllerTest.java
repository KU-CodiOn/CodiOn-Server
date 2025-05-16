package com.example.codionbe.domain.closet.controller;

import com.example.codionbe.domain.closet.dto.response.ImageAnalysisResponse;
import com.example.codionbe.domain.closet.entity.Category;
import com.example.codionbe.domain.closet.entity.PersonalColor;
import com.example.codionbe.domain.closet.exception.ClosetSuccessCode;
import com.example.codionbe.domain.closet.service.ClosetService;
import com.example.codionbe.global.auth.CustomUserDetails;
import com.example.codionbe.global.auth.JwtTokenProvider;
import com.example.codionbe.global.common.success.SuccessResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClosetController.class)
class ClosetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClosetService closetService;

    @MockBean
    private JwtTokenProvider jwtTokenProvider;

    @MockBean
    private UserDetailsService userDetailsService;

    @Test
    @WithMockUser
    @DisplayName("이미지 업로드 및 분석 API 테스트")
    void uploadAndAnalyzeImageApiTest() throws Exception {
        // Given
        MockMultipartFile mockImage = new MockMultipartFile(
                "image",
                "test_image.jpg",
                MediaType.IMAGE_JPEG_VALUE,
                "test image content".getBytes()
        );

        ImageAnalysisResponse mockResponse = ImageAnalysisResponse.builder()
                .imageUrl("https://s3.test.com/bucket/test_image.jpg")
                .category(Category.TOP)
                .personalColor(PersonalColor.SUMMER)
                .color("Light Blue")
                .build();

        when(closetService.uploadAndAnalyzeImage(any())).thenReturn(mockResponse);

        // When & Then
        mockMvc.perform(multipart("/closet/image")
                        .file(mockImage)
                        .contentType(MediaType.MULTIPART_FORM_DATA_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(ClosetSuccessCode.IMAGE_ANALYSIS_SUCCESS.getCode()))
                .andExpect(jsonPath("$.message").value(ClosetSuccessCode.IMAGE_ANALYSIS_SUCCESS.getMessage()))
                .andExpect(jsonPath("$.data.imageUrl").value("https://s3.test.com/bucket/test_image.jpg"))
                .andExpect(jsonPath("$.data.category").value("TOP"))
                .andExpect(jsonPath("$.data.personalColor").value("SUMMER"))
                .andExpect(jsonPath("$.data.color").value("Light Blue"));
    }
} 