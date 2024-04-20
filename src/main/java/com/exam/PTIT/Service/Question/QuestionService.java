package com.exam.PTIT.Service.Question;

import com.exam.PTIT.DTOs.AnswerSheet;
import com.exam.PTIT.DTOs.ExamQuestionPoint;
import com.exam.PTIT.DTOs.QuestionDto;
import com.exam.PTIT.Entity.Question;
import com.exam.PTIT.Entity.QuestionType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface QuestionService {
    ResponseEntity<?> createQuestion(QuestionDto requestQuestion,Long examId);
    ResponseEntity<?> updateQuestion(QuestionDto requestQuestion,Long examId,Long questionId);
    ResponseEntity<?> deleteQuestion(Long examId, Long questionId);
    ResponseEntity<?> getAll(Long ExamId);
}
