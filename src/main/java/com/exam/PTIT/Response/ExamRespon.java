package com.exam.PTIT.Response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ExamRespon {
    private int stt;
    private String name;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="Asia/Ho_Chi_Minh")
    private Date beginExam;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="Asia/Ho_Chi_Minh")
    private Date finishExam;
    private int durationExam;
    private String title;
    private String courseName;
    private String courseCode;
}
