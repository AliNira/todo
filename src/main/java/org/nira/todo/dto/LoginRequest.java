package org.nira.todo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(
        title = "Login Request"
)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    @Schema(
            description = "Username or Email"
    )
    private String usernameOrEmail;
    @Schema(
            description = "Password"
    )
    private String password;
}