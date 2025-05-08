package com.example.codionbe.domain.coordination.service;

import com.example.codionbe.domain.closet.entity.Clothes;
import com.example.codionbe.domain.closet.repository.ClothesRepository;
import com.example.codionbe.domain.coordination.dto.CreateCoordinationRequest;
import com.example.codionbe.domain.coordination.dto.request.CoordinationUpdateRequest;
import com.example.codionbe.domain.coordination.entity.Coordination;
import com.example.codionbe.domain.coordination.exception.CoordinationErrorCode;
import com.example.codionbe.domain.coordination.repository.CoordinationRepository;
import com.example.codionbe.global.common.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CoordinationService {

    private final CoordinationRepository coordinationRepository;
    private final ClothesRepository clothesRepository;

    @Transactional
    public void createCoordination(Long userId, CreateCoordinationRequest request) {
        List<Clothes> clothesList = clothesRepository.findAllById(request.getClothesIds());

        // 착용 횟수 증가
        clothesList.forEach(Clothes::increaseWearingCount);

        Coordination coordination = Coordination.builder()
                .userId(userId)
                .date(request.getDate())
                .clothesList(clothesList)
                .build();

        coordinationRepository.save(coordination);
    }

    @Transactional
    public void updateCoordination(Long userId, CoordinationUpdateRequest request) {
        Coordination coordination = coordinationRepository
                .findByUserIdAndDateAndIsDeletedFalse(userId, request.getDate())
                .orElseThrow(() -> new CustomException(CoordinationErrorCode.COORDINATION_NOT_FOUND));

        // 기존 옷 착용 횟수 감소
        coordination.getClothesList().forEach(Clothes::decreaseWearingCount);

        // 새 옷 ID들 조회
        List<Clothes> newClothes = clothesRepository.findAllById(request.getClothesIds());

        // 삭제되었거나 권한 없는 옷 체크
        for (Clothes clothes : newClothes) {
            if (clothes.isDeleted() || !clothes.getUserId().equals(userId)) {
                throw new CustomException(CoordinationErrorCode.INVALID_CLOTHES);
            }
        }

        // 새 옷 착용 횟수 증가
        newClothes.forEach(Clothes::increaseWearingCount);

        // 옷 교체
        coordination.updateClothes(newClothes);
    }

}
