package org.nira.todo.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.nira.todo.entity.Role;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {

    @NotEmpty
    private String name;

    @NotEmpty
    private String username;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    private String password;

    @NotEmpty
    private Role role;

}
