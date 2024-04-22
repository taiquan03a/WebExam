package com.exam.PTIT.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.domain.Auditable;

import java.util.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "question")
@Builder
public class Question implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "question_text", columnDefinition = "text")
    private String questionText;

    @Column(name="point")
    private int point;

    @Column(name = "exam_id")
    private Long examId;

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "question_id")
    private List<Choice> choices;


}