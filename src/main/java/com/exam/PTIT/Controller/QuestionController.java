package com.exam.PTIT.Controller;

import com.exam.PTIT.DTOs.QuestionDto;
import com.exam.PTIT.Service.Question.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("{examId}/getAll")
    ResponseEntity<?> getAll(@PathVariable Long examId){
        return questionService.getAll(examId);
    }
    @PostMapping("{examId}/createQuestion")
    ResponseEntity<?> createQuestion(@PathVariable Long examId,@RequestBody QuestionDto requestQuestion){
        return questionService.createQuestion(requestQuestion,examId);
    }
    @PutMapping("{examId}/update/")
    ResponseEntity<?> updateQuestion(
            @PathVariable Long examId,
            @RequestBody QuestionDto requestQuestion,
            @RequestParam Long questionId){
        return questionService.updateQuestion(requestQuestion,examId,questionId);
    }
    @DeleteMapping("{examId}/delete/")
    ResponseEntity<?> deleteQuestion(
            @PathVariable Long examId,
            @RequestParam Long questionId){
        return questionService.deleteQuestion(examId,questionId);
    }
}
