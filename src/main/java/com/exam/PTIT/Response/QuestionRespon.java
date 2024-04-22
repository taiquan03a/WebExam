package com.exam.PTIT.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionRespon {
    private String questionText;
    private int point;
    private List<ChoiceRespon> choiceResponList;
}
