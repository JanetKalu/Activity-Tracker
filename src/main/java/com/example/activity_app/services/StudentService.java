package dev.decagon.activitytracker.services;

import dev.decagon.activitytracker.dto.StudentDTO;
import dev.decagon.activitytracker.dto.StudentLoginDTO;
import dev.decagon.activitytracker.entities.Student;

public interface StudentService {

Student createStudent(StudentDTO studentDto);

StudentDTO getStudent(StudentDTO studentDto);

Student studentLogin(StudentLoginDTO studentLoginDTO);

Student findStudentById(Long id);

}
