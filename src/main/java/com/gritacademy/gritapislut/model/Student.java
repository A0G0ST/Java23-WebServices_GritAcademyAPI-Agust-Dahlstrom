package com.gritacademy.gritapislut.model;

import jakarta.persistence.*;


import lombok.Data;


@Entity
@Table(name = "apistudents")
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;  

    @Column(name = "last_name", nullable = false)
    private String lastName;   

    @Column(name = "town")
    private String town;

    
}
