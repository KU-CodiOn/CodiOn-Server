package com.example.codionbe.domain.closet.controller;

import com.example.codionbe.domain.closet.dto.request.RegisterClothesRequest;
import com.example.codionbe.domain.closet.dto.request.ClothesFilterRequest;
import com.example.codionbe.domain.closet.dto.request.UpdateClothesRequest;
import com.example.codionbe.domain.closet.dto.response.ClothesResponse;
import com.example.codionbe.domain.closet.exception.ClosetSuccessCode;
import com.example.codionbe.domain.closet.service.ClosetService;
import com.example.codionbe.global.auth.CustomUserDetails;
import com.example.codionbe.global.common.success.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ClosetController implements com.example.codionbe.domain.closet.controller.ClosetApi {

    private final ClosetService closetService;

    @Override
    public ResponseEntity<SuccessResponse<Void>> registerClothes(
            MultipartFile image,
            RegisterClothesRequest request,
            CustomUserDetails userDetails) throws IOException {

        closetService.registerClothes(userDetails.getUser().getId(), image, request);
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
            MultipartFile image,
            UpdateClothesRequest request,
            CustomUserDetails userDetails
    ) throws IOException {
        closetService.updateClothes(userDetails.getUser().getId(), clothesId, image, request);
        return ResponseEntity.ok(new SuccessResponse<>(ClosetSuccessCode.CLOTHES_UPDATE_SUCCESS, null));
    }
}