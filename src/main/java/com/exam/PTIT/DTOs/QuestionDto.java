package com.exam.PTIT.DTOs;

import com.exam.PTIT.Entity.Choice;
import lombok.Data;

import java.util.List;
@Data
public class QuestionDto {
    private String questionText;
    private int point;
    private List<Choice> choices;
}
