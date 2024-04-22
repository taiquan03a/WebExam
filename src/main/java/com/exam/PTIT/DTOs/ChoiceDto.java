package com.exam.PTIT.DTOs;

import lombok.Data;

@Data
public class ChoiceDto {
    private Long id;
    private String choiceText;
    private boolean answerSelect;
}
