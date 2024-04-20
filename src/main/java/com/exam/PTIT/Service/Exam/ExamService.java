package com.exam.PTIT.Service.Exam;


import com.exam.PTIT.Entity.Exam;
import com.exam.PTIT.Response.ExamDetailRespon;
import com.exam.PTIT.Response.ExamRespon;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ExamService {
    List<ExamRespon> getExamListByPage(Integer pageNo, Integer pageSize, String sortBy);
    ResponseEntity<?> createExam(Exam exam, String username);
    Exam updateExam(Exam requsetExam,String username,Long id);
    boolean deleteExam(Long id);

    ResponseEntity<?> userExamDetail(Long examId);
    List<Exam> adminGetExamListByPage(Integer pageNo, Integer pageSize, String sortBy);
}
