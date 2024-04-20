package com.exam.PTIT.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;
import java.io.Serializable;

@Entity
@Table(name = "course")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "course_code")
    private String courseCode;

    @Column(name = "name")
    private String name;

    @Column(name = "img_url")
    private String imgUrl;

}

