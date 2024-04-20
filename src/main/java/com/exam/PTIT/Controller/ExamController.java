package com.exam.PTIT.Controller;

import com.exam.PTIT.Entity.Exam;
import com.exam.PTIT.Response.ExamRespon;
import com.exam.PTIT.Service.Exam.ExamService;
import com.exam.PTIT.Service.Jwt.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/exam")
public class ExamController {
    @Autowired
    private ExamService examService;
    @Autowired
    private JwtService jwtService;

    @GetMapping("getPageUser")
    public ResponseEntity<?> getExamListByUser(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        List<ExamRespon> list = examService.getExamListByPage(pageNo, pageSize, sortBy);
        return new ResponseEntity<List<ExamRespon>>(list, new HttpHeaders(), HttpStatus.OK);
    }
    @GetMapping("getPageAdmin")
    public ResponseEntity<?> getExamListByAdmin(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy
    ){
        List<Exam> list = examService.adminGetExamListByPage(pageNo, pageSize, sortBy);
        return new ResponseEntity<List<Exam>>(list, new HttpHeaders(), HttpStatus.OK);
    }
    @PostMapping("create")
    public ResponseEntity<?> create(@RequestBody Exam requestExam,@RequestHeader("Authorization") String token){
        token = token.substring(7);
        String username = jwtService.extractUsername(token);
        return examService.createExam(requestExam,username);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(
            @PathVariable Long id
            ,@RequestBody Exam requestExam,
            @RequestHeader("Authorization") String token){
        token = token.substring(7);
        String username = jwtService.extractUsername(token);
        Exam exam = examService.updateExam(requestExam,username,id);
        if (exam == null) return new ResponseEntity<>("update that bai exam_id" + id,HttpStatus.BAD_REQUEST);
        return ResponseEntity.ok(exam);
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        if(!examService.deleteExam(id)) return new ResponseEntity<>("delete that bai",HttpStatus.BAD_REQUEST);
        return ResponseEntity.ok("xoa thanh cong exam_id : " + id);
    }

    @GetMapping("userExamDetail/{examId}")
    public ResponseEntity<?> userExamDetail(@PathVariable Long examId){
        return examService.userExamDetail(examId);
    }
}
