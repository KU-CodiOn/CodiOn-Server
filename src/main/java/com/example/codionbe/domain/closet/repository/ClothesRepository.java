package com.example.codionbe.domain.closet.repository;

import com.example.codionbe.domain.closet.entity.Clothes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClothesRepository extends JpaRepository<Clothes, Long>, ClothesRepositoryCustom {
    List<Clothes> findByUserIdAndIsDeletedFalse(Long userId);
}
