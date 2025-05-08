package com.example.codionbe.domain.coordination.entity;

import com.example.codionbe.domain.closet.entity.Clothes;
import com.example.codionbe.domain.comment.entity.Comment;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Coordination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private LocalDate date;

    private boolean isDeleted = false;

    @ManyToMany
    @JoinTable(
            name = "coordination_clothes",
            joinColumns = @JoinColumn(name = "coordination_id"),
            inverseJoinColumns = @JoinColumn(name = "clothes_id")
    )
    private List<Clothes> clothesList;

    @OneToOne(mappedBy = "coordination", cascade = CascadeType.ALL, orphanRemoval = true)
    private Comment comment;

    public void setComment(Comment comment) {
        this.comment = comment;
    }


    public void updateClothes(List<Clothes> newClothesList) {
        this.clothesList.clear();
        this.clothesList.addAll(newClothesList);
    }

    public void delete() {
        this.isDeleted = true;
    }
}
