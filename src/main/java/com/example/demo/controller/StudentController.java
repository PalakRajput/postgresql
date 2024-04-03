package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<Student> getStudentById(@PathVariable Integer studentId){
        Student student = studentService.getStudentById(studentId);
        return ResponseEntity.ok(student);
    }

    @PostMapping("/")
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        return ResponseEntity.ok().body(studentService.saveStudent(student));
    }

    @GetMapping("/")
    public ResponseEntity<List<Student>> getAllStudents(){
        List<Student> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }
    @GetMapping("/postCode/{postCode}")
    public ResponseEntity<List<Student>> getStudentsByPostCode(@PathVariable String postCode){
        List<Student> students = studentService.getStudentsByPostCode(postCode);
        return ResponseEntity.ok(students);
    }
    @PutMapping("/")
    public ResponseEntity<Student> editStudentRecord(@RequestBody Student student) {
        return ResponseEntity.ok().body(studentService.editStudent(student));
    }

    @DeleteMapping("/")
    public ResponseEntity<String> deleteStudents(@RequestBody List<Integer> studentIds){
        return ResponseEntity.ok().body(studentService.deleteStudents(studentIds));
    }
}
