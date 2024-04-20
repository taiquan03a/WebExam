package com.exam.PTIT.Repository;

import com.exam.PTIT.Entity.Exam;
import org.springframework.data.jpa.repository.JpaRepository;;
import org.springframework.stereotype.Repository;

import java.util.*;
@Repository
public interface ExamRepository extends JpaRepository<Exam,Long> {
    Exam findExamById(Long id);
}
