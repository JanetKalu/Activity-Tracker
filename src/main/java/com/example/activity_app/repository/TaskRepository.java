package dev.decagon.activitytracker.repository;

import dev.decagon.activitytracker.entities.Student;
import dev.decagon.activitytracker.entities.Task;
import dev.decagon.activitytracker.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Optional<Task> findTaskByIdAndStudent(Long taskId, Student student);
    List<Task> findTasksByStudent(Student student);
    List<Task> findTaskByStatusAndStudent(Status status, Student student);

    Optional<Task> findTaskByIdAndStudent_Id(Long taskId, Long studentId);

}
