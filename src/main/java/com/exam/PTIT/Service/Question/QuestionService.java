package com.exam.PTIT.Service.Question;

import com.exam.PTIT.DTOs.QuestionDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionService {
    ResponseEntity<?> createQuestion(QuestionDto requestQuestion,Long examId);
    ResponseEntity<?> updateQuestion(QuestionDto requestQuestion,Long examId,Long questionId);
    ResponseEntity<?> deleteQuestion(Long examId, Long questionId);
    ResponseEntity<?> getAll(Long ExamId);
}
