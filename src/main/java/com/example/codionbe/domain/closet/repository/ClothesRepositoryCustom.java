package com.example.codionbe.domain.closet.repository;

import com.example.codionbe.domain.closet.dto.request.ClothesFilterRequest;
import com.example.codionbe.domain.closet.entity.Clothes;

import java.util.List;

public interface ClothesRepositoryCustom {
    List<Clothes> findByUserIdAndFilter(Long userId, ClothesFilterRequest condition);
}
