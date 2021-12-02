package com.mahlodi.atm.Controller;

import com.mahlodi.atm.DTO.userDTO;
import com.mahlodi.atm.Service.StudentService;
import com.mahlodi.atm.persistence.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;


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
}
