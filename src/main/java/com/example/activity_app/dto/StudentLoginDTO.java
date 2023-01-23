package dev.decagon.activitytracker.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
public class StudentLoginDTO {

    @Email
    @NotNull
    private String email;

    @NotNull
    private String password;

}
