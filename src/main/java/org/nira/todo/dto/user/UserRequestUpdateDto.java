package org.nira.todo.dto.user;


import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestUpdateDto {

    private String name;

    private String username;

    @Email
    private String email;

    private String password;
}
