package com.example.codionbe.domain.closet.service;

import com.example.codionbe.domain.closet.entity.Clothes;
import com.example.codionbe.domain.closet.dto.RegisterClothesRequest;
import com.example.codionbe.domain.closet.repository.ClothesRepository;
import com.example.codionbe.global.s3.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ClosetService {

    private final ClothesRepository clothesRepository;
    private final S3Uploader s3Uploader;

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
}
