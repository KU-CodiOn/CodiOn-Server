package com.example.codionbe.domain.closet.repository;

import com.example.codionbe.domain.closet.dto.request.ClothesFilterRequest;
import com.example.codionbe.domain.closet.entity.Category;
import com.example.codionbe.domain.closet.entity.Clothes;
import com.example.codionbe.domain.closet.entity.QClothes;
import com.example.codionbe.domain.closet.entity.SubCategory;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

import static com.example.codionbe.domain.closet.entity.QClothes.clothes;

@Repository
@RequiredArgsConstructor
public class ClothesRepositoryImpl implements com.example.codionbe.domain.closet.repository.ClothesRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Clothes> findByUserIdAndFilter(Long userId, ClothesFilterRequest c) {
        QClothes clothes = QClothes.clothes;

        BooleanBuilder builder = new BooleanBuilder();

        builder.and(clothes.userId.eq(userId));
        builder.and(clothes.isDeleted.isFalse());

        if (c.getCategory() != null) {
            builder.and(eqCategory(c.getCategory()));
        }

        if (c.getPersonalColor() != null)
            builder.and(clothes.personalColor.eq(c.getPersonalColor()));

        if (c.getColor() != null)
            builder.and(clothes.color.eq(c.getColor()));

        if (c.getIsFavorite() != null)
            builder.and(clothes.isFavorite.eq(c.getIsFavorite()));

        if (c.getIsWearableWhenRainy() != null)
            builder.and(clothes.suitableForRain.eq(c.getIsWearableWhenRainy()));

        // 상황 키워드: 단순 문자열 포함 여부 (DB 문자열 → contains)
        if (c.getSituationKeywords() != null && !c.getSituationKeywords().isEmpty()) {
            BooleanBuilder keywordBuilder = new BooleanBuilder();
            for (String keyword : c.getSituationKeywords()) {
                keywordBuilder.or(clothes.situationKeywords.containsIgnoreCase(keyword));
            }
            builder.and(keywordBuilder);
        }

        return queryFactory
                .selectFrom(clothes)
                .where(builder)
                .orderBy(clothes.wearCount.desc())
                .fetch();
    }

    private BooleanExpression eqCategory(Category category) {
        return category != null ? clothes.subCategory.stringValue().in(
                Arrays.stream(SubCategory.values())
                        .filter(sc -> sc.getParentCategory() == category)
                        .map(Enum::name)
                        .toList()
        ) : null;
    }
}
