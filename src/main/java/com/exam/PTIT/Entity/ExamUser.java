package com.exam.PTIT.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "exam_user")
@Builder
public class ExamUser{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "exam_id")
    private Exam exam;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private UserInfo user;

    @Column(name = "is_started", columnDefinition = "TINYINT")
    //@Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean isStarted = false;

    @Column(name = "time_start")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeStart;

    @Column(name = "time_finish")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeFinish;

    @JsonIgnore
    @Column(name="answer_sheet", columnDefinition = "text")
    private String answerSheet;

    @Column(name = "is_finished", columnDefinition = "TINYINT")
    //@Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean isFinished = false;

    @Column(name = "remaining_time")
    private int remainingTime;

    @Column(name = "total_point")
    private Double totalPoint;

}


