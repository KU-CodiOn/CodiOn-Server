package com.example.codionbe.domain.closet.exception;

import com.example.codionbe.global.common.success.SuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ClosetSuccessCode implements SuccessCode {

    CLOTHES_REGISTER_SUCCESS(HttpStatus.OK, "CLOSET_001", "옷 등록에 성공했습니다."),
    CLOTHES_LIST_SUCCESS(HttpStatus.OK, "CLOSET_002", "MY 옷장 목록 조회에 성공했습니다."),
    CLOTHES_UPDATE_SUCCESS(HttpStatus.OK,"CLOSET_003", "옷 수정에 성공했습니다."),
    CLOTHES_DELETE_SUCCESS(HttpStatus.OK, "CLOSET_004", "옷 삭제에 성공했습니다."),
    FAVORITE_TOGGLE_SUCCESS(HttpStatus.OK, "CLOSET_005", "즐겨찾기 상태가 변경되었습니다."),
    CLOTHES_DETAIL_SUCCESS(HttpStatus.OK, "CLOSET_006", "옷 상세 조회에 성공했습니다."),
    IMAGE_ANALYSIS_SUCCESS(HttpStatus.OK, "CLOSET_007", "이미지 분석에 성공했습니다.");


    private final HttpStatus status;
    private final String code;
    private final String message;
}
