package com.exam.PTIT.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "choice")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Choice implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "choice_text")
    private String choiceText;
    @Column(name = "answer")
    private boolean answer;
    @Column(name = "corrected")
    private int isCorrected;

    @JsonIgnore
    @Column(name="question_id")
    private Long questionId;
}
