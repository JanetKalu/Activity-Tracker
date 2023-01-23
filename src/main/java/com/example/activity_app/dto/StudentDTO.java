package dev.decagon.activitytracker.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class StudentDTO {

    @NotBlank(message = "First Name must not be blank")
    private String firstName;

    @NotBlank(message = "Last Name must not be blank")
    private String lastName;
    @Email
    private String email;

    @NotBlank(message = "Password must not Be blank")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;


    public StudentDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

}
