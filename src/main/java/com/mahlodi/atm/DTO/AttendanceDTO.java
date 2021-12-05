package com.mahlodi.atm.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AttendanceDTO {
    private Long id;
    private Stat status;
    private LocalDate date;
    private String fullNames;

    public AttendanceDTO() {
    }
    public AttendanceDTO(Long id, String fullNAmes) {
        this.id = id;
        this.status = Stat.ABSENT;
        this.date = LocalDate.now();
        this.fullNames = fullNAmes;
    }


}
