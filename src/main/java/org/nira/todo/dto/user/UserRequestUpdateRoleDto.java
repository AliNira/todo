package org.nira.todo.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.nira.todo.entity.Role;

@Schema(
        title = "User Update Role Request"
)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestUpdateRoleDto {
    @Schema(
            description = "User Role"
    )
    @NotEmpty
    private Role role;
}
