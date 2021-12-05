package com.mahlodi.atm.persistence.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "attendance")
@Data
public class Attendance  {
    @Id
    @GeneratedValue
    private Long id;
    private boolean active = true;
    @ManyToOne(targetEntity = Student.class,cascade = javax.persistence.CascadeType.ALL
    ,fetch = FetchType.LAZY)
    private Student student;
    private LocalDate date;

    public Attendance() {
    }

    public Attendance( Student student) {
        this.active = true;
        this.student = student;
        this.date = LocalDate.now();
    }
}
