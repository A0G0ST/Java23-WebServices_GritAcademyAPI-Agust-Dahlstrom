package com.gritacademy.gritapislut.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gritacademy.gritapislut.model.Course;

import java.util.List;


@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByCourseName(String courseName);          
    List<Course> findByCourseNameContaining(String keyword);    
    List<Course> findByDescriptionContaining(String keyword);   
}
