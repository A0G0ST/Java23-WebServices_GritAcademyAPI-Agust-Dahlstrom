package com.gritacademy.gritapislut.controller;

import com.gritacademy.gritapislut.model.Student;
import com.gritacademy.gritapislut.model.Course;
import com.gritacademy.gritapislut.model.StudentCourse;
import com.gritacademy.gritapislut.repository.StudentRepository;
import com.gritacademy.gritapislut.repository.CourseRepository;
import com.gritacademy.gritapislut.repository.StudentCourseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class GritAcademyController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentCourseRepository studentCourseRepository;

    // 1. Hämta alla studenter
    @GetMapping("/students")
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        if (students.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null); 
        }
        return ResponseEntity.ok(students); 
    }

    // 2. Hämta alla kurser
    @GetMapping("/courses")
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        if (courses.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null); 
        }
        return ResponseEntity.ok(courses); 
    }

    // 3. Hämta alla associationer mellan studenter och kurser
    @GetMapping("/associations")
    public ResponseEntity<List<StudentCourse>> getAllAssociations() {
        List<StudentCourse> associations = studentCourseRepository.findAll();
        if (associations.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null); 
        }
        return ResponseEntity.ok(associations); 
    }

    // 4. Hämta en student med specifikt id och dess kurser
    @GetMapping("/students/{id}/courses")
    public ResponseEntity<List<Course>> getCoursesByStudentId(@PathVariable Long id) {
        List<Course> courses = studentCourseRepository.findByStudentId(id)
                                                      .stream()
                                                      .map(StudentCourse::getCourse)
                                                      .collect(Collectors.toList());
        if (courses.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); 
        }
        return ResponseEntity.ok(courses); 
    }

    // 5. Hämta alla studenter med specifikt förnamn och deras kurser
    @GetMapping("/associations/students/firstname/{firstName}")
    public ResponseEntity<List<StudentCourse>> getAssociationsByStudentFirstName(@PathVariable String firstName) {
        List<StudentCourse> associations = studentCourseRepository.findAssociationsByStudentFirstName(firstName);
        if (associations.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); 
        }
        return ResponseEntity.ok(associations); 
    }

    // 6. Hämta alla studenter med ett specifikt efternamn och deras kurser
    @GetMapping("/associations/students/lastname/{lastName}")
    public ResponseEntity<List<StudentCourse>> getAssociationsByStudentLastName(@PathVariable String lastName) {
        List<StudentCourse> associations = studentCourseRepository.findAssociationsByStudentLastName(lastName);
        if (associations.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); 
        }
        return ResponseEntity.ok(associations); 
    }

    // 7. Hämta alla studenter från en specifik stad och deras kurser
    @GetMapping("/associations/students/town/{town}")
    public ResponseEntity<List<StudentCourse>> getAssociationsByStudentTown(@PathVariable String town) {
        List<StudentCourse> associations = studentCourseRepository.findAssociationsByStudentTown(town);
        if (associations.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); 
        }
        return ResponseEntity.ok(associations); 
    }

    // 8. Hämta en kurs med specifikt id och dess studenter
    @GetMapping("/courses/{id}/students")
    public ResponseEntity<List<Student>> getStudentsByCourseId(@PathVariable Long id) {
        List<Student> students = studentCourseRepository.findByCourseId(id)
                                                        .stream()
                                                        .map(StudentCourse::getStudent)
                                                        .collect(Collectors.toList());
        if (students.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); 
        }
        return ResponseEntity.ok(students); 
    }

    // 9. Hämta en kurs med specifikt namn och dess studenter
    @GetMapping("/courses/name/{courseName}/students")
    public ResponseEntity<List<StudentCourse>> getStudentsByCourseName(@PathVariable String courseName) {
        List<StudentCourse> associations = studentCourseRepository.findAssociationsByCourseName(courseName);
        if (associations.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); 
        }
        return ResponseEntity.ok(associations); 
    }

    // 10. Hämta en kurs med specifikt ord i namnet
    @GetMapping("/courses/name/contains/{keyword}")
    public ResponseEntity<List<Course>> getCoursesByNameContaining(@PathVariable String keyword) {
        List<Course> courses = courseRepository.findByCourseNameContaining(keyword);
        if (courses.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); 
        }
        return ResponseEntity.ok(courses); 
    }

    // 11. Hämta en kurs med specifikt ord i beskrivningen
    @GetMapping("/courses/description/contains/{keyword}")
    public ResponseEntity<List<Course>> getCoursesByDescriptionContaining(@PathVariable String keyword) {
        List<Course> courses = courseRepository.findByDescriptionContaining(keyword);
        if (courses.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); 
        }
        return ResponseEntity.ok(courses); 
    }
}

