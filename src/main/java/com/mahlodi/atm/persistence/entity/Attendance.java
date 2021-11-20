package com.mahlodi.atm.persistence.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table(name = "attendance")
@Data
public class Attendance  {
    @Id
    @GeneratedValue
    private Long id;
    private boolean active = true;
    @ManyToOne(targetEntity = User.class,cascade = javax.persistence.CascadeType.ALL
    ,fetch = FetchType.LAZY)
    private User user;
}
