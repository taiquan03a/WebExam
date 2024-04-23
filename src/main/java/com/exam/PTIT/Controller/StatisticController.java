package com.exam.PTIT.Controller;

import com.exam.PTIT.Entity.Exam;
import com.exam.PTIT.Service.Exam.ExamService;
import com.exam.PTIT.Service.ExamUser.ExamUserService;
import com.exam.PTIT.Service.Statistic.StatictisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/statistic")
public class StatisticController {
    @Autowired
    private StatictisService statictisService;
    @GetMapping("/countUser")
    public ResponseEntity<?> countUser(){
        return statictisService.countUser();
    }

}
