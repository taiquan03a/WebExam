package com.exam.PTIT.Response;

import lombok.Builder;
import lombok.Data;
import java.util.*;

@Data
@Builder
public class ExamDetailRespon {
        private int durationExam;
        private String name;
        List<QuestionRespon> questionResponList;
}
