package com.example.codionbe.domain.comment.entity;

import com.example.codionbe.domain.coordination.entity.Coordination;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mood; // "좋음", "보통", "나쁨"
    private String content;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coordination_id")
    private Coordination coordination;

    public void update(String mood, String content) {
        this.mood = mood;
        this.content = content;
    }
}

