package com.exam.PTIT.Service.Statistic;

import com.exam.PTIT.Entity.Exam;
import com.exam.PTIT.Entity.ExamUser;
import com.exam.PTIT.Repository.ExamRepository;
import com.exam.PTIT.Repository.ExamUserRepository;
import com.exam.PTIT.Response.ExamStatisticsCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatictisServiceImpl implements StatictisService{
    @Autowired
    private ExamRepository examRepository;
    @Autowired
    private ExamUserRepository examUserRepository;
    @Override
    public ResponseEntity<?> countUser() {
        List<Exam> exams = examRepository.findAll();
        List<ExamStatisticsCount> examStatisticsCounts = new ArrayList<>();
        for(Exam exam : exams){
            List<ExamUser> examUsers = examUserRepository.findAllByExamId(exam.getId());
            double remainingTime = 0;
            double pointAverage = 0;
            for(ExamUser examUser : examUsers){
                remainingTime += examUser.getRemainingTime();
                pointAverage += examUser.getTotalPoint();
            }
            remainingTime /= examUsers.size();
            pointAverage /= examUsers.size();
            ExamStatisticsCount examStatisticsCount = ExamStatisticsCount.builder()
                    .id(exam.getId())
                    .nameExam(exam.getName())
                    .countUser(examUserRepository.countExamUserByExamId(exam.getId()))
                    .completion(remainingTime)
                    .pointAverage(pointAverage)
                    .build();
            examStatisticsCounts.add(examStatisticsCount);
        }
        return ResponseEntity.ok(examStatisticsCounts);
    }


}
