package com.exam.PTIT.Service.Course;

import com.exam.PTIT.Entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    Optional<Course> getCourseById(Long id);

    List<Course> getCourseList();

    List<Course> getCourseListByPage(Integer pageNo, Integer pageSize, String sortBy);

    void saveCourse(Course course);

    void delete(Long id);

    boolean existsByCode(String code);

    boolean existsById(Long id);
    boolean updateCourse(Course newCourse,Long id);
    boolean deleteCourse(Long id);
}
