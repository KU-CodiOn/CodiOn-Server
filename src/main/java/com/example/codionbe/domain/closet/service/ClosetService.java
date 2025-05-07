package com.example.codionbe.domain.closet.service;

import com.example.codionbe.domain.closet.dto.request.ClothesFilterRequest;
import com.example.codionbe.domain.closet.dto.request.UpdateClothesRequest;
import com.example.codionbe.domain.closet.dto.response.ClothesResponse;
import com.example.codionbe.domain.closet.dto.response.FavoriteToggleResponse;
import com.example.codionbe.domain.closet.entity.Clothes;
import com.example.codionbe.domain.closet.dto.request.RegisterClothesRequest;
import com.example.codionbe.domain.closet.exception.ClosetErrorCode;
import com.example.codionbe.domain.closet.repository.ClothesRepository;
import com.example.codionbe.global.common.exception.CustomException;
import com.example.codionbe.global.s3.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

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
