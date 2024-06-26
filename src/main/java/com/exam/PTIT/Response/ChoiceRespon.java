package com.exam.PTIT.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChoiceRespon {
    private Long id;
    private String choiceText;
    private Boolean answer;
    private Boolean answerSelect;
}
