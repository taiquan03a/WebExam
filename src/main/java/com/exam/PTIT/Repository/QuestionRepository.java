package com.exam.PTIT.Repository;

import com.exam.PTIT.Entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findAllByExamId(Long examId);
}
