package com.exam.PTIT.Response;

import lombok.Builder;
import lombok.Data;

import java.util.*;

@Data
@Builder
public class ExamResult {
    private  String examName;
    private Double totalPoint;
    List<QuestionRespon> questionResponList;
}
