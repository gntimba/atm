package com.mahlodi.atm.Controller;

import com.mahlodi.atm.DTO.AttDTO;
import com.mahlodi.atm.DTO.AttendanceDTO;
import com.mahlodi.atm.DTO.StudentAttendanceDTO;
import com.mahlodi.atm.DTO.userDTO;
import com.mahlodi.atm.Service.AttendanceService;
import com.mahlodi.atm.Service.StudentService;
import com.mahlodi.atm.persistence.entity.Attendance;
import com.mahlodi.atm.persistence.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;

    @Autowired
    private AttendanceService attendanceService;


    @PostMapping("/student")
    public ResponseEntity<?> saveStudent(@RequestBody Student student) {
        ResponseEntity<Student> resp = null;
        try {
            Student id = studentService.save(student);
            resp = new ResponseEntity<Student>(
                    id, HttpStatus.CREATED
            );
            return resp;
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(
                    "Unable to save User",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/student")
    public ResponseEntity<?> getStudent() {
        ResponseEntity<List<Student>> resp = null;
        try {
            List<Student> id = studentService.findAll();
            resp = new ResponseEntity<List<Student>>(
                    id, HttpStatus.CREATED
            );
            return resp;
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(
                    "users not found",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/addAttendance")
    public ResponseEntity<?> addStudent(@RequestBody AttDTO id) {
        ResponseEntity<Attendance> resp = null;
        Attendance att = attendanceService.saveAttendance(id.getId());
        resp = new ResponseEntity<Attendance>(
                att, HttpStatus.CREATED
        );
        return resp;
    }

    @GetMapping("/getAttendance")
    public ResponseEntity<?> getAttendance() {
        ResponseEntity<List<AttendanceDTO>> resp = null;
        try {
            List<AttendanceDTO> id = attendanceService.findTodayAttendance();
            resp = new ResponseEntity<List<AttendanceDTO>>(
                    id, HttpStatus.OK
            );
            return resp;
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(
                    "users not found",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/getByWeek")
    public ResponseEntity<?> getByWeek(@RequestParam(required = false) Long id) {
        if (id == null) {
            List<StudentAttendanceDTO> attendanceDTOS = attendanceService.findAllByWeek();
            return new ResponseEntity<List<StudentAttendanceDTO>>(
                    attendanceDTOS, HttpStatus.OK
            );
        } else {
            StudentAttendanceDTO attendanceDTOS = attendanceService.getWeeklyStudentAttendance(id);
            return new ResponseEntity<StudentAttendanceDTO>(
                    attendanceDTOS, HttpStatus.OK
            );
        }
    }

    @GetMapping("/getByMonth")
    public ResponseEntity<?> getByMonth(@RequestParam(required = false) Long id) {
        if (id == null) {
            List<StudentAttendanceDTO> attendanceDTOS = attendanceService.findAllByMonth();
            return new ResponseEntity<List<StudentAttendanceDTO>>(
                    attendanceDTOS, HttpStatus.OK
            );
        } else {
            StudentAttendanceDTO attendanceDTOS = attendanceService.getMonthlyStudentAttendance(id);
            return new ResponseEntity<StudentAttendanceDTO>(
                    attendanceDTOS, HttpStatus.OK
            );
        }
    }
}
