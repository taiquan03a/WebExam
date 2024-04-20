package com.exam.PTIT.Controller;

import com.exam.PTIT.DTOs.PageResult;
import com.exam.PTIT.Entity.Course;
import com.exam.PTIT.Service.Course.CourseService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

import java.util.*;

@RestController
@RequestMapping("api/v1/course")
public class CourseController {
    @Autowired
    private CourseService courseService;
    @GetMapping(value = "/course-list")
    //@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public List<Course> getAllCourse() {
        List<Course> courseList = courseService.getCourseList();
        return courseList;
    }
    @GetMapping(value = "/page")
    public ResponseEntity<List<Course>> getCourseListByPage(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        List<Course> list = courseService.getCourseListByPage(pageNo, pageSize, sortBy);

        return new ResponseEntity<List<Course>>(list, new HttpHeaders(), HttpStatus.OK);
    }
    @GetMapping(value = "/{id}/check-course-code")
    public boolean checkCourseCode(@RequestParam String value, @PathVariable Long id) {
        if (courseService.existsByCode(value)) {
            if (courseService.getCourseById(id).get().getCourseCode().equals(value)) {
                return false;
            }
            return true;
        }
        return false;
    }
    @GetMapping(value = "/check-course-code")
    public boolean checkCode(@RequestParam String value) {
        return courseService.existsByCode(value);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getCourseById(@PathVariable Long id) {
        Optional<Course> course = courseService.getCourseById(id);
        if (!course.isPresent()) {
            throw new EntityNotFoundException("Not found with course id: " + id);
        }
        return ResponseEntity.ok().body(course);
    }
    @PostMapping(value = "/create")
    public ResponseEntity<?> createCourse(@RequestBody Course course) {
        try {
            if (!courseService.existsByCode(course.getCourseCode())) {
                courseService.saveCourse(course);
                return ResponseEntity.ok("tao thanh cong" + course);

            } else {
                return ResponseEntity.badRequest().body( "Duplicate Course!" + course.getCourseCode());
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.toString());
        }
    }
    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> updateCourse(@RequestBody Course newCourse, @PathVariable Long id) {
        if(!courseService.updateCourse(newCourse,id)){
            return new ResponseEntity<String>("ko tim thay id = " +  id,HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok("thanh cong " + id);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable Long id){
        if(!courseService.deleteCourse(id)){
            return new ResponseEntity<String>("ko tim thay id = " +  id,HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok("thanh cong " + id);
    }


}
