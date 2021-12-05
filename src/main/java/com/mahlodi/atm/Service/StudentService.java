package com.mahlodi.atm.Service;


import com.mahlodi.atm.persistence.Dao.StudentDao;
import com.mahlodi.atm.persistence.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentDao studentDao;

    public Student save(Student student) {
        return studentDao.save(student);
    }

    public List<Student> findAll() {
        return studentDao.findAll();
    }

    public Student findById(Long id) {
        return studentDao.findById(id).orElse(null);
    }

    public void delete(Long id) {
        studentDao.deleteById(id);
    }



}
