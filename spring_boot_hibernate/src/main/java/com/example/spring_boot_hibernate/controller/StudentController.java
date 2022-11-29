package com.example.spring_boot_hibernate.controller;

import com.example.spring_boot_hibernate.model.Student;
import com.example.spring_boot_hibernate.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    @PostMapping("/save")
    public Boolean save(@RequestBody Student student){

        studentService.saveStudent(student);

        return true;
    }

    @GetMapping("/list")
    public List<Student> list(){

        return studentService.getStudents();
    }

    @DeleteMapping()
    public Boolean delete(@RequestBody Student student){

        return studentService.delete(student);
    }

}
