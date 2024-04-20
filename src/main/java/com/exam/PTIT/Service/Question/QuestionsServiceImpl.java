package com.exam.PTIT.Service.Question;


import com.exam.PTIT.DTOs.QuestionDto;
import com.exam.PTIT.Entity.Choice;
import com.exam.PTIT.Entity.Question;
import com.exam.PTIT.Repository.ChoiceRepository;
import com.exam.PTIT.Repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class QuestionsServiceImpl implements QuestionService{
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private ChoiceRepository choiceRepository;

    @Override
    public ResponseEntity<?> getAll(Long examId){
        return ResponseEntity.ok(questionRepository.findAllByExamId(examId));
    }
    @Override
    public ResponseEntity<?> createQuestion(QuestionDto requestQuestion,Long examId){
        try {
            List<Choice> listChoice = new ArrayList<>();
            Question question = Question.builder()
                    .examId(examId)
                    .questionText(requestQuestion.getQuestionText())
                    .point(requestQuestion.getPoint())
                    .questionType(null)
                    .build();
            questionRepository.save(question);
            Long questionId = question.getId();
            for (Choice requestChoice : requestQuestion.getChoices()) {
                Choice choice = Choice.builder()
                        .choiceText(requestChoice.getChoiceText())
                        .answer(requestChoice.isAnswer())
                        .questionId(questionId)
                        .build();
                choiceRepository.save(choice);
                listChoice.add(choice);
            }
            question.setChoices(listChoice);
            questionRepository.save(question);
            return new ResponseEntity<>(question, HttpStatus.CREATED);
        }catch (Exception ex){
            return new ResponseEntity<>(ex,HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> updateQuestion(QuestionDto requestQuestion, Long examId, Long questionId) {
        Question question = questionRepository.findById(questionId).get();
        if(question == null) return new ResponseEntity<>("Not found",HttpStatus.NOT_FOUND);
        List<Question> questionList = questionRepository.findAllByExamId(examId);
        boolean check = true;
        for(Question questionCheck : questionList){
            if(questionId == questionCheck.getId()){
                check = false;
                break;
            }
        }
        if(check) return new ResponseEntity<>("Ko ton tai cau hoi id "+ questionId+" trong ki thi id " + examId,HttpStatus.NOT_FOUND);
        try{
            question.setQuestionText(requestQuestion.getQuestionText());
            question.setPoint(requestQuestion.getPoint());
            question.setChoices(requestQuestion.getChoices());
            questionRepository.save(question);
            return new ResponseEntity<>("update thanh cong",HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>("update That bai",HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> deleteQuestion(Long examId, Long questionId) {
        Question question = questionRepository.findById(questionId).get();
        if(question == null) return new ResponseEntity<>("Not found",HttpStatus.NOT_FOUND);
        List<Question> questionList = questionRepository.findAllByExamId(examId);
        boolean check = true;
        for(Question questionCheck : questionList){
            if(questionId == questionCheck.getId()){
                check = false;
                break;
            }
        }
        if(check) {
            return new ResponseEntity<>("Ko ton tai cau hoi id "+ questionId+" trong ki thi id " + examId,HttpStatus.NOT_FOUND);
        }
        questionRepository.delete(question);
        return new ResponseEntity<>("xoa thanh cong cau hoi id "+ questionId,HttpStatus.OK);
    }
}
