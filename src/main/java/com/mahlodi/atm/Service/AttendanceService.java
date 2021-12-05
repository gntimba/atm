package com.mahlodi.atm.Service;

import com.mahlodi.atm.DTO.AttendanceDTO;
import com.mahlodi.atm.DTO.Stat;
import com.mahlodi.atm.Exception.AttendanceExist;
import com.mahlodi.atm.Exception.NotFoundException;
import com.mahlodi.atm.persistence.Dao.AttendanceDao;
import com.mahlodi.atm.persistence.entity.Attendance;
import com.mahlodi.atm.persistence.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class AttendanceService {

    @Autowired
    private StudentService studentService;

    @Autowired
    private AttendanceDao attendanceDao;

    public Attendance saveAttendance(Long id) {
        Student student = studentService.findById(id);
        if (student != null) {
            Attendance attendance = attendanceDao.findTodayAttendance(student);
            if (attendance != null) {
                throw new AttendanceExist("Attendance already exist " + attendance.getStudent().getStudentNo());
            }
            return attendanceDao.save(new Attendance(student));
        } else {
            throw new NotFoundException("Student not found " + id);
        }

    }

    public List<AttendanceDTO> findTodayAttendance() {
        List<AttendanceDTO> attendanceDTOS = new ArrayList<>();
        List<Student> students = studentService.findAll();
        List<Attendance> attendances = attendanceDao.findTodayPresentAttendance();
        for (Student student : students) {
            Long id = student.getStudentNo();
            String fullNames = student.getFirstName() + " " + student.getLastName();
            AttendanceDTO attendanceDTO = new AttendanceDTO(id, fullNames);
            for (Attendance attendance : attendances) {
                if (attendance.getStudent().getStudentNo().equals(id)) {
                    attendanceDTO.setStatus(Stat.PRESENT);
                    break;
                }
            }
            attendanceDTOS.add(attendanceDTO);
        }
        return attendanceDTOS;
    }
}
