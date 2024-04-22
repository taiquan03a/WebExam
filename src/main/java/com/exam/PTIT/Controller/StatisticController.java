package com.exam.PTIT.Controller;

import com.exam.PTIT.Entity.Exam;
import com.exam.PTIT.Service.Exam.ExamService;
import com.exam.PTIT.Service.ExamUser.ExamUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/statistic")
public class StatisticController {
    @Autowired
    private ExamService examService;
    @Autowired
    private ExamUserService examUserService;

}
