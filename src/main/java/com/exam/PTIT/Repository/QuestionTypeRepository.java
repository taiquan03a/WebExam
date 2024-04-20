package com.exam.PTIT.Repository;

import com.exam.PTIT.Entity.QuestionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuestionTypeRepository extends JpaRepository<QuestionType, Long> {
    boolean existsById(Long id);

//    Optional<QuestionType> findAllByTypeCode(EQTypeCode typeCode);
}
