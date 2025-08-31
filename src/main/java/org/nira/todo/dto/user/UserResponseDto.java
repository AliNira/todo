package org.nira.todo.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.nira.todo.entity.Role;

@Schema(
        title = "User Response"
)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {

    @Schema(
            description = "User id"
    )
    private Long id;

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
    private String email;

    @Schema(
            description = "User role"
    )
    private Role role;

}
