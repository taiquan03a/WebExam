package com.exam.PTIT.Service.Exam;

import com.exam.PTIT.Entity.Choice;
import com.exam.PTIT.Entity.Exam;
import com.exam.PTIT.Entity.Question;
import com.exam.PTIT.Repository.ExamRepository;
import com.exam.PTIT.Response.ChoiceRespon;
import com.exam.PTIT.Response.ExamDetailRespon;
import com.exam.PTIT.Response.ExamRespon;
import com.exam.PTIT.Response.QuestionRespon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class ExamServiceImpl implements ExamService{
    @Autowired
    private ExamRepository examRepository;
    @Override
    public List<ExamRespon> getExamListByPage(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Exam> examPage = examRepository.findAll(paging);
        List<ExamRespon> examResponPage = new ArrayList<>();
        if(examPage.hasContent()) {
            int stt = 1;
            for(Exam exam : examPage.getContent()){
                boolean locked = autoUpdate(exam);
                if(locked) {
                    ExamRespon examRespon = ExamRespon.builder()
                            .stt(stt)
                            .name(exam.getName())
                            .beginExam(exam.getBeginExam())
                            .finishExam(exam.getFinishExam())
                            .durationExam(exam.getDurationExam())
                            .courseName(exam.getCourse().getName())
                            .courseCode(exam.getCourse().getCourseCode())
                            .title(exam.getTitle())
                            .build();
                    examResponPage.add(examRespon);
                    stt++;
                }
            }
            return examResponPage;
        }
        return null;
    }
    @Override
    public List<Exam> adminGetExamListByPage(Integer pageNo, Integer pageSize, String sortBy){
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Exam> examPage = examRepository.findAll(paging);
//        for(Exam exam : examPage.getContent()){
//            if(autoUpdate(exam)) exam.setStatus(1);
//            else exam.setStatus(0);
//        }
        return examPage.getContent();
    }

    @Override
    public ResponseEntity<?> createExam(Exam requestExam, String username) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(requestExam.getBeginExam());
        calendar.add(Calendar.MINUTE, requestExam.getDurationExam());
        Date sumTime = calendar.getTime();
        System.out.println(requestExam.getBeginExam());
        System.out.println(sumTime);
        System.out.println(requestExam.getFinishExam());
        if(sumTime.compareTo(requestExam.getFinishExam()) > 0) {
            return new ResponseEntity<>("begin time + duration time > finish time", HttpStatus.BAD_REQUEST);
        }
        Exam exam = Exam.builder()
                .beginExam(requestExam.getBeginExam())
                .durationExam(requestExam.getDurationExam())
                .finishExam(requestExam.getFinishExam())
//                .status(1)
                .title(requestExam.getTitle())
                .createdDate(new Date())
                .createdBy(username)
                .course(requestExam.getCourse())
                .build();

        examRepository.save(exam);
        return ResponseEntity.ok(examRepository.findExamById(exam.getId()));
    }
    @Override
    public Exam updateExam(Exam requestExam,String username,Long id){
        Exam exam = examRepository.findExamById(id);
        if(exam == null) return null;
        exam.setBeginExam(requestExam.getBeginExam());
        exam.setFinishExam(requestExam.getFinishExam());
        exam.setDurationExam(requestExam.getDurationExam());
        exam.setCourse(requestExam.getCourse());
        exam.setTitle(requestExam.getTitle());
        exam.setLastModifiedBy(username);
        exam.setLastModifiedDate(new Date());
        examRepository.save(exam);
        return exam;
    }
    @Override
    public boolean deleteExam(Long id){
        Exam exam = examRepository.findExamById(id);
        if(exam == null) return false;
        examRepository.delete(exam);
        return true;
    }

    @Override
    public ResponseEntity<?> userExamDetail(Long examId) {
        Exam exam = examRepository.findExamById(examId);
        List<QuestionRespon> questionResponList = new ArrayList<>();
        for(Question question : exam.getQuestions()){

            QuestionRespon questionRespon = QuestionRespon
                    .builder()
                    .choiceResponList(listChoiceRespon(question))
                    .point(question.getPoint())
                    .questionText(question.getQuestionText())
                    .build();
            questionResponList.add(questionRespon);
        }
        ExamDetailRespon examDetailRespon = ExamDetailRespon
                .builder()
                .durationExam(exam.getDurationExam())
                .name(exam.getName())
                .questionResponList(questionResponList)
                .build();
        return new ResponseEntity<>(examDetailRespon,HttpStatus.OK);
    }
    private List<ChoiceRespon> listChoiceRespon(Question question){
        List<ChoiceRespon> choiceResponList = new ArrayList<>();
        for(Choice choice : question.getChoices()){
            ChoiceRespon choiceRespon = ChoiceRespon
                    .builder()
                    .id(choice.getId())
                    .answer(choice.isAnswer())
                    .choiceText(choice.getChoiceText())
                    .build();
            choiceResponList.add(choiceRespon);
        }
        return choiceResponList;
    }
    private boolean autoUpdate(Exam exam){
        Date currentTime = new Date();
        Date beginTime = exam.getBeginExam();
        Date finishTime = exam.getFinishExam();
        if(currentTime.compareTo(beginTime) >= 0 && currentTime.compareTo(finishTime) < 0)  return true;
        return false;
    }
}
