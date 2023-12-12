package com.example.quizproject_final.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "answers")

public class Answers {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer answer_id;

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private Questions questions;

    @Column(nullable = false)
    private String text;

    @Column(nullable = false)
    private boolean isCorrect;
}
