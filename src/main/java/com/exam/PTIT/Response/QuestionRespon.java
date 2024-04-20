package com.exam.PTIT.Response;

import lombok.Builder;
import lombok.Data;

import java.util.*;

@Data
@Builder
public class QuestionRespon {
    private String questionText;
    private int point;
    private List<ChoiceRespon> choiceResponList;
}
