package com.gritacademy.gritapislut.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gritacademy.gritapislut.model.StudentCourse;

import java.util.List;


@Repository
public interface StudentCourseRepository extends JpaRepository<StudentCourse, Long> {
    
    List<StudentCourse> findByStudentId(Long studentId);
    List<StudentCourse> findByCourseId(Long courseId);
   
    @Query("SELECT sc FROM StudentCourse sc JOIN sc.student s WHERE s.firstName = :firstName")
    List<StudentCourse> findAssociationsByStudentFirstName(@Param("firstName") String firstName);
    
    @Query("SELECT sc FROM StudentCourse sc JOIN sc.student s WHERE s.lastName = :lastName")
    List<StudentCourse> findAssociationsByStudentLastName(@Param("lastName") String lastName);

    @Query("SELECT sc FROM StudentCourse sc JOIN sc.student s WHERE s.town = :town")
    List<StudentCourse> findAssociationsByStudentTown(@Param("town") String town);

    @Query("SELECT sc FROM StudentCourse sc JOIN sc.course c WHERE c.courseName = :courseName")
    List<StudentCourse> findAssociationsByCourseName(@Param("courseName") String courseName);
}
