package com.exam.PTIT.Service.ExamUser;

import com.exam.PTIT.DTOs.ExamUserDto;
import com.exam.PTIT.Entity.Exam;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;

public interface ExamUserService {
    ResponseEntity<?> createUserExam(String username, Long examId, ExamUserDto examUserDto) throws JsonProcessingException;
    ResponseEntity<?> userListByExam(Long examId);
    ResponseEntity<?> examListByUser(String username);
    ResponseEntity<?> ExamResult (Long examId,String username) throws JsonProcessingException;
}
