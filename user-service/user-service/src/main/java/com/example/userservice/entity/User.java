package com.example.userservice.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "TLM_USER")
@Table
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String email;
    @Column
    private Long departmentId;
}
