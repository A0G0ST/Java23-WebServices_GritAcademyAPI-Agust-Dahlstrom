package com.gritacademy.gritapislut.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "apistudents_apicourses")
public class StudentCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    
    public Student getStudent() {
        return student;
    }

   
    public Course getCourse() {
        return course;
    }

    
}