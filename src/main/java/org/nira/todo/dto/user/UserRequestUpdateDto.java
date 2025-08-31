package org.nira.todo.dto.user;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(
        title = "User Update Request"
)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestUpdateDto {

    @Schema(
            description = "User name"
    )
    private String name;

    @Schema(
            description = "User username"
    )
    private String username;

    @Schema(
            description = "User email"
    )
    @Email
    private String email;

    @Schema(
            description = "User password"
    )
    private String password;
}
