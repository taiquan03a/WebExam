package com.exam.PTIT.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExamStatisticsCount {
    private Long id;
    private String nameExam;
    private Long countUser;
    private double completion;
    private double pointAverage;
}
