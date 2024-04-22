package com.exam.PTIT.Service.ExamUser;

import com.exam.PTIT.DTOs.AnswerSheet;
import com.exam.PTIT.DTOs.ChoiceDto;
import com.exam.PTIT.DTOs.ExamUserDto;
import com.exam.PTIT.Entity.*;
import com.exam.PTIT.Repository.ExamRepository;
import com.exam.PTIT.Repository.ExamUserRepository;
import com.exam.PTIT.Repository.QuestionRepository;
import com.exam.PTIT.Repository.UserInfoRepository;
import com.exam.PTIT.Response.ChoiceRespon;
import com.exam.PTIT.Response.ExamRespon;
import com.exam.PTIT.Response.QuestionRespon;
import com.exam.PTIT.Response.UserRespon;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExamUserServiceImpl implements ExamUserService {
    @Autowired
    private ExamUserRepository examUserRepository;
    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private ExamRepository examRepository;
    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public ResponseEntity<?> createUserExam(String username, Long examId, ExamUserDto examUserDto) throws JsonProcessingException {
        Exam exam = examRepository.findExamById(examId);
        if(exam == null) return new ResponseEntity<>("bài thi chưa mở hoặc bài thi không tồn tài!!!", HttpStatus.BAD_REQUEST);
        UserInfo userInfo = userInfoRepository.findByName(username).get();
        List<QuestionRespon> questionResponList = new ArrayList<>();
        int duration = exam.getDurationExam();
        int totalPoint = 0;
        for(AnswerSheet answerSheet : examUserDto.getAnswerSheetList()){
            Long questionId = answerSheet.getQuestionId();
            Question question = questionRepository.findById(questionId).get();
            int point = question.getPoint();
            List<Choice> choices = question.getChoices();
            List<ChoiceDto> choiceDtos = answerSheet.getChoiceDtos();
            List<ChoiceRespon> choiceRespons = new ArrayList<>();
            int index = choiceDtos.size();
            for(int i = 0; i < choices.size(); i++){
                Long choiceId = choices.get(i).getId();
                boolean answer = choices.get(i).isAnswer();
                if (index > 0) {
                    boolean answerSelect = choiceDtos.get(i).isAnswerSelect();
                    ChoiceRespon choiceRespon = ChoiceRespon
                            .builder()
                            .id(choiceId)
                            .choiceText(choices.get(i).getChoiceText())
                            .answer(answer)
                            .answerSelect(answerSelect)
                            .build();
                    choiceRespons.add(choiceRespon);
                    if((answer == true) && (answerSelect == true)) {
                        totalPoint+= question.getPoint();
                        System.out.println(totalPoint);
                    }
                }else {
                    ChoiceRespon choiceRespon = ChoiceRespon
                            .builder()
                            .id(choiceId)
                            .choiceText(choices.get(i).getChoiceText())
                            .answer(answer)
                            .answerSelect(null)
                            .build();
                    choiceRespons.add(choiceRespon);
                }
                index--;
            }
            QuestionRespon questionRespon = QuestionRespon
                    .builder()
                    .questionText(question.getQuestionText())
                    .point(question.getPoint())
                    .choiceResponList(choiceRespons)
                    .build();
            questionResponList.add(questionRespon);
        }
        Double totalPoint10 = (double) (totalPoint*10)/exam.getQuestions().size();
        System.out.println(totalPoint10);
        ObjectMapper objectMapper = new ObjectMapper();
        String answerJson = objectMapper.writeValueAsString(questionResponList);
        ExamUser examUser = ExamUser
                .builder()
                .user(userInfo)
                .exam(exam)
                .answerSheet(answerJson)
                .timeStart(examUserDto.getTimeStart())
                .timeFinish(examUserDto.getTimeFinish())
                .totalPoint(totalPoint10)
                .build();
        examUserRepository.save(examUser);
        return ResponseEntity.ok(examUser);
    }

    @Override
    public ResponseEntity<?> userListByExam(Long examId) {
        Exam exam = examRepository.findExamById(examId);
        if (exam == null) return  new ResponseEntity<>("exam không tồn tại",HttpStatus.NOT_FOUND);
        List<ExamUser> examUsers = examUserRepository.findAllByExamId(examId);
        List<UserRespon> userRespons = new ArrayList<>();
        for (ExamUser examuser : examUsers){
            UserRespon userRespon = UserRespon
                    .builder()
                    .email(examuser.getUser().getEmail())
                    .username(examuser.getUser().getName())
                    .build();
            userRespons.add(userRespon);
        }
        return ResponseEntity.ok(userRespons);
    }

    @Override
    public ResponseEntity<?> examListByUser(String username) {
        UserInfo userInfo = userInfoRepository.findByName(username).get();
        if(userInfo == null) return new  ResponseEntity<>("người dùng không tồn tại!!!",HttpStatus.NOT_FOUND);
        List<ExamUser> examUsers = examUserRepository.findAllByUserName(username);
        List<ExamRespon> examRespons = new ArrayList<>();
        for(ExamUser examUser : examUsers){
            ExamRespon examRespon = ExamRespon
                    .builder()
                    .name(examUser.getExam().getName())
                    .beginExam(examUser.getExam().getBeginExam())
                    .finishExam(examUser.getExam().getFinishExam())
                    .title(examUser.getExam().getTitle())
                    .durationExam(examUser.getExam().getDurationExam())
                    .courseName(examUser.getExam().getCourse().getName())
                    .courseCode(examUser.getExam().getCourse().getCourseCode())
                    .build();
            examRespons.add(examRespon);
        }
        return ResponseEntity.ok(examRespons);
    }

    @Override
    public ResponseEntity<?> ExamResult(Long examId, String username) throws JsonProcessingException {
        List<ExamUser> examUsers = examUserRepository.findAllByUserNameAndExamId(username,examId);
        List<List<QuestionRespon>> questions = new ArrayList<>();
//        for(ExamUser examUser : examUsers){
//            String answerSheet = examUser.getAnswerSheet();
//            ObjectMapper mapper = new ObjectMapper();
//            List<QuestionRespon> questionResponList = mapper.readValue(answerSheet, new TypeReference<List<QuestionRespon>>() {});
//            //questions.add(questionResponList);
//            if(questionResponList.size() != 0) return ResponseEntity.ok(questionResponList);
//        }
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonCarArray =
                "[{ \"color\" : \"Black\", \"type\" : \"BMW\" }, { \"color\" : \"Red\", \"type\" : \"FIAT\" }]";
        //List<Car> listCar = objectMapper.readValue(jsonCarArray, new TypeReference<List<Car>>(){});
        List<QuestionRespon> questionResponList = objectMapper.readValue(examUsers.get(4).getAnswerSheet(), new TypeReference<List<QuestionRespon>>() {});
        //return ResponseEntity.ok(questions);
        return new ResponseEntity<>(questionResponList,HttpStatus.OK);
    }
}
