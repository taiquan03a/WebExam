package com.exam.PTIT.DTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.*;

@Data
public class ExamUserDto {
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="Asia/Ho_Chi_Minh")
    private Date timeStart;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="Asia/Ho_Chi_Minh")
    private Date timeFinish;
    private List<AnswerSheet> answerSheetList;
}
