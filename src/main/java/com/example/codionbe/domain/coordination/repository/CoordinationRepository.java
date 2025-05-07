package com.example.codionbe.domain.coordination.repository;

import com.example.codionbe.domain.coordination.entity.Coordination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface CoordinationRepository extends JpaRepository<Coordination, Long> {

    // 유저의 코디 전체 조회
    List<Coordination> findAllByUserIdAndIsDeletedFalseOrderByDateDesc(Long userId);

    // 날짜 기준으로 코디 1개 찾기 (수정, 삭제, 상세조회용)
    Optional<Coordination> findByUserIdAndDateAndIsDeletedFalse(Long userId, LocalDate date);

    // 특정 코디 ID로 존재 여부 확인 (예: 상세조회, 삭제, 수정 등)
    Optional<Coordination> findByIdAndIsDeletedFalse(Long coordinationId);
}
