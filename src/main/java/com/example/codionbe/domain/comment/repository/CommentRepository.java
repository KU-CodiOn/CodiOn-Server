package com.example.codionbe.domain.comment.repository;

import com.example.codionbe.domain.comment.entity.Comment;
import com.example.codionbe.domain.coordination.entity.Coordination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    Optional<Comment> findByCoordination(Coordination coordination);
}
