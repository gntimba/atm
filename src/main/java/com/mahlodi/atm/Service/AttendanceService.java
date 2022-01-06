package com.mahlodi.atm.Service;

import com.mahlodi.atm.DTO.AttendanceDTO;
import com.mahlodi.atm.DTO.Stat;
import com.mahlodi.atm.DTO.StudentAttendanceDTO;
import com.mahlodi.atm.Exception.AttendanceExist;
import com.mahlodi.atm.Exception.NotFoundException;
import com.mahlodi.atm.persistence.Dao.AttendanceDao;
import com.mahlodi.atm.persistence.entity.Attendance;
import com.mahlodi.atm.persistence.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    public List<AttendanceDTO> findTodayAttendance( LocalDate date) {
        List<AttendanceDTO> attendanceDTOS = new ArrayList<>();
        List<Student> students = studentService.findAll();
        List<Attendance> attendances = attendanceDao.findTodayPresentAttendance(date);
        for (Student student : students) {
            Long id = student.getStudentNo();
            String fullNames = student.getFirstName() + " " + student.getLastName();
            AttendanceDTO attendanceDTO = new AttendanceDTO(id, fullNames,date);
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

    public List<StudentAttendanceDTO> findAllByWeek() {
        List<StudentAttendanceDTO> attendanceDTOS = new ArrayList<>();
        List<Student> students = studentService.findAll();
        for (Student student : students) {
            StudentAttendanceDTO studentAttendanceDTO = new StudentAttendanceDTO();
            studentAttendanceDTO.setStudent(student);
            List<Attendance> attendances = attendanceDao.findAttendanceWeeklyID(student.getStudentNo());
            attendances.forEach(attendance -> studentAttendanceDTO.addAttendance(attendance));
            attendanceDTOS.add(studentAttendanceDTO);
        }
        return attendanceDTOS;

    }

    public List<StudentAttendanceDTO> findAllByMonth() {
        List<StudentAttendanceDTO> attendanceDTOS = new ArrayList<>();
        List<Student> students = studentService.findAll();
        for (Student student : students) {
            StudentAttendanceDTO studentAttendanceDTO = new StudentAttendanceDTO();
            studentAttendanceDTO.setStudent(student);
            List<Attendance> attendances = attendanceDao.findAttendanceMonthlyID(student.getStudentNo());
            attendances.forEach(attendance -> studentAttendanceDTO.addAttendance(attendance));
            attendanceDTOS.add(studentAttendanceDTO);
        }
        return attendanceDTOS;

    }

    public StudentAttendanceDTO getWeeklyStudentAttendance(Long id) {
        Student student = studentService.findById(id);
        if(student == null)
            throw new NotFoundException("No student found for id " + id);
        StudentAttendanceDTO studentAttendanceDTO = new StudentAttendanceDTO();
        studentAttendanceDTO.setStudent(student);
        List<Attendance> attendances = attendanceDao.findAttendanceWeeklyID(id);
        if(attendances.isEmpty())
            throw new NotFoundException("No attendance found for student " + id);
        attendances.forEach(attendance -> studentAttendanceDTO.addAttendance(attendance));
        return studentAttendanceDTO;
    }

    public StudentAttendanceDTO getMonthlyStudentAttendance(Long id) {
        Student student = studentService.findById(id);
        if(student == null)
            throw new NotFoundException("No student found for id " + id);
        StudentAttendanceDTO studentAttendanceDTO = new StudentAttendanceDTO();
        studentAttendanceDTO.setStudent(student);
        List<Attendance> attendances = attendanceDao.findAttendanceMonthlyID(id);
        if(attendances.isEmpty())
            throw new NotFoundException("No attendance found for student " + id);
        attendances.forEach(attendance -> studentAttendanceDTO.addAttendance(attendance));
        return studentAttendanceDTO;
    }
}
