package com.example.codionbe.domain.coordination.service;

import com.example.codionbe.domain.closet.entity.Clothes;
import com.example.codionbe.domain.closet.repository.ClothesRepository;
import com.example.codionbe.domain.coordination.dto.CreateCoordinationRequest;
import com.example.codionbe.domain.coordination.entity.Coordination;
import com.example.codionbe.domain.coordination.repository.CoordinationRepository;
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
}
