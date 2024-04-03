package com.example.demo.repository;

import com.example.demo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    @Modifying
    @Query(value = "DELETE FROM STUDENT WHERE STUDENT_ID IN :studentIds", nativeQuery = true)
    Integer deleteByStudentIds(@Param("studentIds") List<Integer> studentIds);

    @Query(value = "SELECT * FROM student WHERE additional_info->>'postCode' = :postCode", nativeQuery = true)
    List<Student> findStudentByPostCode(@Param("postCode") String postCode);
}
