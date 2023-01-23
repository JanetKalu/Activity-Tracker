package dev.decagon.activitytracker.services.impl;

import dev.decagon.activitytracker.dto.StudentDTO;
import dev.decagon.activitytracker.dto.StudentLoginDTO;
import dev.decagon.activitytracker.entities.Student;
import dev.decagon.activitytracker.exception.BadRequestException;
import dev.decagon.activitytracker.exception.NotFoundException;
import dev.decagon.activitytracker.repository.StudentRepository;
import dev.decagon.activitytracker.services.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Optional;

@AllArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
//    public StudentDTO createStudent(StudentDTO studentDto) {
//        Student student = new Student();
//
//        BeanUtils.copyProperties(studentDto, student);
//
//        boolean emailTaken = studentRepository.existsByEmail(studentDto.getEmail());
//
//        if(emailTaken){
//            throw new BadRequestException("Email Already Taken");
//        }

//    Student createdStudent = studentRepository.save(student);
//
//    StudentDTO createdStudentDto = new StudentDTO();
//
//        BeanUtils.copyProperties(createdStudent, createdStudentDto);
//
//        return studentDto;
//}
//




        public Student createStudent(StudentDTO studentDto) {

        boolean emailTaken = studentRepository.existsByEmail(studentDto.getEmail());

        if(emailTaken){
            throw new BadRequestException("Email Already Taken");
        }
            Student student = new Student();
           student.setFirstName(studentDto.getFirstName());
           student.setLastName(studentDto.getLastName());
           student.setEmail(studentDto.getEmail());
           student.setPassword(studentDto.getPassword());
           student.setCreatedAt(LocalDateTime.now().now());
           student.setLastModified(LocalDateTime.now());



            BeanUtils.copyProperties(student, studentDto);
            Student createdStudent = studentRepository.save(student);

       StudentDTO createdStudentDto = new StudentDTO();

       BeanUtils.copyProperties(createdStudent, createdStudentDto);

        return createdStudent;
    }

    public StudentDTO getStudent(StudentDTO studentDto){
        if(studentDto.getEmail() == null || studentDto.getPassword() == null){
            throw new BadRequestException("Please complete all fields");
        }

        Optional<Student> findStudent = studentRepository.findStudentByEmail(studentDto.getEmail());

        if(findStudent.isEmpty()){
            throw new BadRequestException("Student does not exist");
        }

        Student foundStudent = findStudent.get();
        if(!foundStudent.getPassword().equals(studentDto.getPassword())){
            throw new BadRequestException("Password is incorrect");
        }


        BeanUtils.copyProperties(foundStudent, studentDto);
        return studentDto;
    }

    @Override
    public Student studentLogin(StudentLoginDTO studentLoginDTO) {
        Student student = studentRepository.findStudentByEmailAndPassword(studentLoginDTO.getEmail(),studentLoginDTO.getPassword())
                .orElseThrow(()-> new NotFoundException("Invalid Credential"));
        return student;
    }

    @Override
    public Student findStudentById(Long id) {

        return studentRepository.findStudentById(id).get();
    }
}
