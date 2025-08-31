package org.nira.todo.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.nira.todo.entity.Role;

@Schema(
        title = "User Request"
)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {

    @Schema(
            description = "User name"
    )
    @NotEmpty
    private String name;

    @Schema(
            description = "User username"
    )
    @NotEmpty
    private String username;

    @Schema(
            description = "User email"
    )
    @NotEmpty
    @Email
    private String email;

    @Schema(
            description = "User password"
    )
    @NotEmpty
    private String password;

    @Schema(
            description = "User role"
    )
    @NotEmpty
    private Role role;

}
