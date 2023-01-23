package dev.decagon.activitytracker.dto;

import dev.decagon.activitytracker.entities.Student;
import dev.decagon.activitytracker.enums.Status;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TaskDTO {
    @NotBlank(message = "Task Title Must Not Be Blank")
    private String title;

    @NotBlank(message = "Task Description Must Not Be Blank")
    private String description;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Task status must not be empty")
    private Status status;

    @NotNull
    private LocalDate completedAt;



    private Student student;
}
