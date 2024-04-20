package com.exam.PTIT.Repository;

import com.exam.PTIT.Entity.ExamUser;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface ExamUserRepository extends JpaRepository<ExamUser,Long> {
//    List<ExamUser> findAllByUserName(String username);
//    //List<ExamUser> findAllByUser_UsernameAndExam_Canceled(String username, boolean canceled);
//    ExamUser findByExam_IdAndUser_Username(Long examId, String username);
//    List<ExamUser> findAllByExam_Part_Course_IdAndUser_UsernameAndTotalPointIsGreaterThan(Long courseId, String username, Double point);
//    List<ExamUser> findAllByExam_Id(Long examId);
//    List<ExamUser> findExamUsersByOrderByTimeFinish();
//    List<ExamUser> findExamUsersByIsFinishedIsTrueAndExam_Id(Long examId);
}
