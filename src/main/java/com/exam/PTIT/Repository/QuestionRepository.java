package com.exam.PTIT.Repository;

import com.exam.PTIT.Entity.Exam;
import com.exam.PTIT.Entity.Question;
import com.exam.PTIT.Entity.QuestionType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findAllByExamId(Long examId);
}
