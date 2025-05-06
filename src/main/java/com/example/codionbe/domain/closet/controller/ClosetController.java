package com.example.codionbe.domain.closet.controller;

import com.example.codionbe.domain.closet.dto.RegisterClothesRequest;
import com.example.codionbe.domain.closet.exception.ClosetSuccessCode;
import com.example.codionbe.domain.closet.service.ClosetService;
import com.example.codionbe.global.auth.CustomUserDetails;
import com.example.codionbe.global.common.success.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class ClosetController implements ClosetApi {

    private final ClosetService closetService;

    @Override
    public ResponseEntity<SuccessResponse<Void>> registerClothes(
            MultipartFile image,
            RegisterClothesRequest request,
            CustomUserDetails userDetails) throws IOException {

        closetService.registerClothes(userDetails.getUser().getId(), image, request);
        return ResponseEntity.ok(new SuccessResponse<>(ClosetSuccessCode.CLOTHES_REGISTER_SUCCESS, null));
    }
}