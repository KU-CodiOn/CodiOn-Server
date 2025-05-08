package com.example.codionbe.domain.comment.service;

import com.example.codionbe.domain.comment.dto.CommentCreateRequest;
import com.example.codionbe.domain.comment.dto.request.CommentUpdateRequest;
import com.example.codionbe.domain.comment.entity.Comment;
import com.example.codionbe.domain.comment.exception.CommentErrorCode;
import com.example.codionbe.domain.comment.repository.CommentRepository;
import com.example.codionbe.domain.coordination.entity.Coordination;
import com.example.codionbe.domain.coordination.exception.CoordinationErrorCode;
import com.example.codionbe.domain.coordination.repository.CoordinationRepository;
import com.example.codionbe.global.common.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CoordinationRepository coordinationRepository;
    private final CommentRepository commentRepository;
    @Transactional
    public void createComment(Long userId, CommentCreateRequest request) {
        Coordination coordination = coordinationRepository
                .findByUserIdAndDateAndIsDeletedFalse(userId, request.getDate())
                .orElseThrow(() -> new CustomException(CoordinationErrorCode.COORDINATION_NOT_FOUND));

        if (coordination.getComment() != null) {
            throw new CustomException(CommentErrorCode.COMMENT_ALREADY_EXISTS);
        }

        Comment comment = Comment.builder()
                .mood(request.getMood())
                .content(request.getContent())
                .coordination(coordination)
                .build();

        coordination.setComment(comment); // 양방향 연관
    }

    @Transactional
    public void updateComment(Long userId, CommentUpdateRequest request) {
        Coordination coordination = coordinationRepository
                .findByUserIdAndDateAndIsDeletedFalse(userId, request.getDate())
                .orElseThrow(() -> new CustomException(CommentErrorCode.COORDINATION_NOT_FOUND));

        Comment comment = commentRepository.findByCoordination(coordination)
                .orElseThrow(() -> new CustomException(CommentErrorCode.COMMENT_NOT_FOUND));

        comment.update(request.getMood(), request.getContent());
    }
}
