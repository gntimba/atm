package com.mahlodi.atm.persistence.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Student {
    @Id
    @Column(name = "id", nullable = false)
    private Long studentNo;
    private String firstName;
    private String lastName;

    public Student() {
    }

    public Student(Long studentNo, String firstName, String lastName) {
        this.studentNo = studentNo;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
