package dev.decagon.activitytracker.controller;

import dev.decagon.activitytracker.dto.TaskDTO;
import dev.decagon.activitytracker.services.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/students")
@AllArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @GetMapping("/view-tasks")
    public ResponseEntity<List<TaskDTO>> getTasks(@PathVariable Long studentId){
        List<TaskDTO> tasks = taskService.getTasks(studentId);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }
    @PostMapping("/{studentId}/create-task")
    public ResponseEntity<String> createTas (@PathVariable Long studentId, @Valid @RequestBody TaskDTO taskDto){
        taskService.createTask(studentId, taskDto);
        return new ResponseEntity<>("New Task Added", HttpStatus.CREATED);
    }

    @GetMapping("/Student/{taskId}")
    public ResponseEntity<TaskDTO> getTask(@PathVariable("taskId") Long studentId, @PathVariable Long taskId){
        TaskDTO task = taskService.getTask(studentId, taskId);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @GetMapping("/status")
    public ResponseEntity<List<TaskDTO>> getTasksByStatus(@PathVariable Long studentId,
                                                          @RequestParam("status") String status){

        List<TaskDTO> tasks = taskService.getTasksByStatus(studentId, status);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @PutMapping("{taskId}/status")
    public ResponseEntity<TaskDTO> updateTaskStatus(@PathVariable Long studentId,
                                                    @PathVariable Long taskId,
                                                    @RequestParam("status") String status){

        TaskDTO task = taskService.updateTaskStatus(studentId, taskId, status);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @PutMapping("{taskId}")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable Long studentId,
                                              @PathVariable Long taskId,
                                              @RequestParam(value = "title", required = false) String title,
                                              @RequestParam(value = "description", required = false) String description){

        TaskDTO task = taskService.updateTask(studentId, taskId, title, description);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @DeleteMapping("{taskId}")
    public ResponseEntity<String> deleteTask(@PathVariable Long studentId,
                                             @PathVariable Long taskId){

        taskService.deleteTask(studentId, taskId);
        return new ResponseEntity<>("Task Deleted Successfully", HttpStatus.NO_CONTENT);
    }
}
