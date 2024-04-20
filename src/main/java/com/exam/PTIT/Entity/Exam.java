package com.exam.PTIT.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;
import java.io.Serializable;

@Entity
@Table(name = "exam")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Exam implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;
    @Column(name = "title")
    private String title;

    @Column(name = "duration_exam")
    private int durationExam;

    @Column(name = "begin_exam")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="Asia/Ho_Chi_Minh")
    private Date beginExam;

    @Column(name = "finish_exam")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="Asia/Ho_Chi_Minh")
    private Date finishExam;

//    @Column(name = "status")
//    private int status;

    @Column(name="question_data", columnDefinition = "text")
    private String questionData;

    @Column(name = "created_date")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="Asia/Ho_Chi_Minh")
    private Date createdDate;

    @Column(name = "last_modified_date")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="Asia/Ho_Chi_Minh")
    private Date lastModifiedDate;

    @Column(name = "last_modified_by")
    private String lastModifiedBy;
    @Column(name = "created_by")
    private String createdBy;

    @ManyToOne()
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "exam_id")
    private List<Question> questions;
}

