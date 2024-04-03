package com.example.demo.service;

import com.example.demo.exception.StudentException;
import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Transactional
    public Student getStudentById(Integer studentId) {
        Optional<Student> student = studentRepository.findById(studentId);
        if (student.isPresent()) {
            return student.get();
        }
        log.error("Student with id = {} is not present.", studentId);
        throw new StudentException("Student not found.", "404");
    }

    @Transactional
    public List<Student> getStudentsByPostCode(String postCode) {
        List<Student> students = studentRepository.findStudentByPostCode(postCode);
        log.info("No of students fetched: {}", students.size());
        if (students.isEmpty()) {
            throw new StudentException("No students found for the given postcode.", "404");
        }
        return students;
    }

    @Transactional
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Transactional
    public List<Student> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        log.info("No of students fetched: {}", students.size());
        if (students.isEmpty()) {
            throw new StudentException("No students found.", "404");
        }
        return students;
    }

    @Transactional
    public Student editStudent(Student student) {
        return studentRepository.save(student);
    }

    @Transactional
    public String deleteStudents(List<Integer> studentIds) {
        Integer noOfRecordsDeleted = studentRepository.deleteByStudentIds(studentIds);
        log.info("No of records deleted: {}", noOfRecordsDeleted);
        return "Successfully deleted " + noOfRecordsDeleted + " records.";
    }
}
