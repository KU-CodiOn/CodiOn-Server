package com.example.codionbe.domain.closet.controller;

import com.example.codionbe.domain.closet.dto.request.RegisterClothesRequest;
import com.example.codionbe.domain.closet.dto.request.ClothesFilterRequest;
import com.example.codionbe.domain.closet.dto.request.UpdateClothesRequest;
import com.example.codionbe.domain.closet.dto.response.ClothesResponse;
import com.example.codionbe.domain.closet.dto.response.FavoriteToggleResponse;
import com.example.codionbe.domain.closet.dto.response.ImageAnalysisResponse;
import com.example.codionbe.domain.closet.exception.ClosetSuccessCode;
import com.example.codionbe.domain.closet.service.ClosetService;
import com.example.codionbe.global.auth.CustomUserDetails;
import com.example.codionbe.global.common.success.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ClosetController implements ClosetApi {

    private final ClosetService closetService;

    @Override
    public ResponseEntity<SuccessResponse<ImageAnalysisResponse>> uploadAndAnalyzeImage(MultipartFile image, CustomUserDetails userDetails) throws IOException {
        ImageAnalysisResponse response = closetService.uploadAndAnalyzeImage(image);
        return ResponseEntity.ok(new SuccessResponse<>(ClosetSuccessCode.IMAGE_ANALYSIS_SUCCESS, response));
    }

    @Override
    public ResponseEntity<SuccessResponse<Void>> registerClothes(
            RegisterClothesRequest request,
            CustomUserDetails userDetails) throws IOException {

        closetService.registerClothes(userDetails.getUser().getId(), request);
        return ResponseEntity.ok(new SuccessResponse<>(ClosetSuccessCode.CLOTHES_REGISTER_SUCCESS, null));
    }

    @Override
    public ResponseEntity<SuccessResponse<List<ClothesResponse>>> getClothesList(
            CustomUserDetails userDetails,
            ClothesFilterRequest filterRequest
    ) {
        List<ClothesResponse> clothesList = closetService.getClothesList(userDetails.getUser().getId(), filterRequest);
        return ResponseEntity.ok(new SuccessResponse<>(ClosetSuccessCode.CLOTHES_LIST_SUCCESS, clothesList));
    }

    @Override
    public ResponseEntity<SuccessResponse<Void>> updateClothes(
            Long clothesId,
            UpdateClothesRequest request,
            CustomUserDetails userDetails
    ) throws IOException {
        closetService.updateClothes(userDetails.getUser().getId(), clothesId, request);
        return ResponseEntity.ok(new SuccessResponse<>(ClosetSuccessCode.CLOTHES_UPDATE_SUCCESS, null));
    }

    @Override
    public ResponseEntity<SuccessResponse<ClothesResponse>> getClothesDetail(Long clothesId, CustomUserDetails userDetails) {
        ClothesResponse response = closetService.getClothesDetail(userDetails.getUser().getId(), clothesId);
        return ResponseEntity.ok(new SuccessResponse<>(ClosetSuccessCode.CLOTHES_DETAIL_SUCCESS, response));
    }

    @Override
    public ResponseEntity<SuccessResponse<Void>> deleteClothes(Long clothesId, CustomUserDetails userDetails) {
        closetService.deleteClothes(userDetails.getUser().getId(), clothesId);
        return ResponseEntity.ok(new SuccessResponse<>(ClosetSuccessCode.CLOTHES_DELETE_SUCCESS, null));
    }

    @Override
    public ResponseEntity<SuccessResponse<FavoriteToggleResponse>> toggleFavorite(Long clothesId, CustomUserDetails userDetails) {
        FavoriteToggleResponse response = closetService.toggleFavorite(userDetails.getUser().getId(), clothesId);
        return ResponseEntity.ok(new SuccessResponse<>(ClosetSuccessCode.FAVORITE_TOGGLE_SUCCESS, response));
    }
}