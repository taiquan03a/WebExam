package com.exam.PTIT.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerSheet {
    private Long questionId;
    private List<ChoiceDto> choiceDtos;
}
