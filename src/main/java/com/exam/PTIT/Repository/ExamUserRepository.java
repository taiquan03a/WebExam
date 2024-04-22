package com.exam.PTIT.Repository;

import com.exam.PTIT.Entity.ExamUser;
import com.exam.PTIT.Entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ExamUserRepository extends JpaRepository<ExamUser,Long> {
    List<ExamUser> findAllByUserName(String username);
    List<ExamUser> findAllByUserNameAndExamId(String username,Long examId);
    List<ExamUser> findAllByExamId(Long examId);
    Long countExamUserByUserName(String username);
    Long countExamUserByExamId(Long examId);

//    List<ExamUser> findExamUsersByOrderByTimeFinish();
//    List<ExamUser> findExamUsersByIsFinishedIsTrueAndExam_Id(Long examId);
}
