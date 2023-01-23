package dev.decagon.activitytracker.controller;

import dev.decagon.activitytracker.dto.StudentDTO;
import dev.decagon.activitytracker.dto.StudentLoginDTO;
import dev.decagon.activitytracker.entities.Student;
import dev.decagon.activitytracker.services.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/Students")
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;

//    @GetMapping("/")
//    public StudentDTO getStudent(@RequestBody StudentDTO studentDto){
//
//        return studentService.getStudent(studentDtO);
//
//    }
    @PostMapping("/login-new-student")
    public ResponseEntity<Student> loginNewStudent(@Valid @RequestBody StudentLoginDTO
                                                            studentLoginDTO, HttpSession httpSession) {
        Student student = studentService.studentLogin(studentLoginDTO);
        httpSession.setAttribute("studentId", student.getId());
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Student fetchStudentById(@PathVariable(value = "id") Long id) {
        return studentService.findStudentById(id);
    }

    @PostMapping("/registration")
    public ResponseEntity<?> createStudent(@Valid @RequestBody StudentDTO studentDto){
        studentService.createStudent(studentDto);
        return new ResponseEntity<>(studentDto,HttpStatus.CREATED);
    }




}
