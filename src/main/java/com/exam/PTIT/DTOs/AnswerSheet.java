package com.exam.PTIT.DTOs;

import com.exam.PTIT.Entity.Choice;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerSheet {
    private Long questionId;
    private List<Choice> choices;
    private Integer point;
}
