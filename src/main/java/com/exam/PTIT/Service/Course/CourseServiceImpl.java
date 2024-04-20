package com.exam.PTIT.Service.Course;

import com.exam.PTIT.Entity.Course;
import com.exam.PTIT.Repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Optional<Course> getCourseById(Long id) {
        return courseRepository.findById(id);
    }

    @Override
    public List<Course> getCourseList() {
        return courseRepository.findAll();
    }

    @Override
    public List<Course> getCourseListByPage(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Course> coursePage = courseRepository.findAll(paging);
        if(coursePage.hasContent()) {
            return coursePage.getContent();
        } else {
            return courseRepository.findAll();
        }
    }

    @Override
    public void saveCourse(Course course) {
        courseRepository.save(course);
    }

    @Override
    public void delete(Long id) {
        courseRepository.deleteById(id);
    }

    @Override
    public boolean existsByCode(String code) {
        return courseRepository.existsByCourseCode(code);
    }

    @Override
    public boolean existsById(Long id) {
        return courseRepository.existsById(id);
    }

    @Override
    public boolean updateCourse(Course newCourse,Long id){
        Course course = courseRepository.findCourseById(id);
        if (course == null) return false;
        course.setCourseCode(newCourse.getCourseCode());
        course.setName(newCourse.getName());
        course.setImgUrl(newCourse.getImgUrl());
        courseRepository.save(course);
        return true;
    }
    @Override
    public boolean deleteCourse(Long id){
        Course course = courseRepository.findCourseById(id);
        if (course == null) return false;
        courseRepository.delete(course);
        return true;
    }

}
