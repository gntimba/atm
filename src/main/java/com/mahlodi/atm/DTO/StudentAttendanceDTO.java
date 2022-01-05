package com.mahlodi.atm.DTO;

import com.mahlodi.atm.persistence.entity.Attendance;
import com.mahlodi.atm.persistence.entity.Student;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class StudentAttendanceDTO  implements Serializable {
    private Student student;
    private List<Attendance> attendance = new ArrayList<>();

    public void addAttendance(Attendance attendance){
        this.attendance.add(attendance);
    }
}
