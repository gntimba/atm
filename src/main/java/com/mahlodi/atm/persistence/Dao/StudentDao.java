package com.mahlodi.atm.persistence.Dao;


import com.mahlodi.atm.persistence.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(exported = false)
public interface StudentDao extends JpaRepository<Student, Long> {
}
