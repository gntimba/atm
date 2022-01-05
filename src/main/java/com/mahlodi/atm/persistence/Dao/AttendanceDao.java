package com.mahlodi.atm.persistence.Dao;

import com.mahlodi.atm.persistence.entity.Attendance;
import com.mahlodi.atm.persistence.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RepositoryRestResource(exported = false)
public interface AttendanceDao extends JpaRepository<Attendance, Long> {

    @Query("select a from Attendance a where a.date = CURRENT_DATE and a.student = ?1")
    Attendance findTodayAttendance(Student student);

    @Query("select a from Attendance a where a.date = CURRENT_DATE")
    List<Attendance> findTodayPresentAttendance();

    @Query(value = "SELECT * FROM attendance WHERE WEEK(date) = WEEK(NOW()) AND student_id =?1", nativeQuery = true)
    List<Attendance> findAttendanceWeeklyID(Long id);

    @Query(value = "SELECT * FROM attendance WHERE MONTH(date) = MONTH(NOW()) AND student_id =?1", nativeQuery = true)
    List<Attendance> findAttendanceMonthlyID(Long id);
}
