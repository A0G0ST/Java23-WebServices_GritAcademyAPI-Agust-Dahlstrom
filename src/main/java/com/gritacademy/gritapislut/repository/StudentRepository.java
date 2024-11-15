package com.gritacademy.gritapislut.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gritacademy.gritapislut.model.Student;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    
    
    List<Student> findByFirstName(String firstName);  
    List<Student> findByLastName(String lastName);    
    List<Student> findByTown(String town);            
    
    
    
}

