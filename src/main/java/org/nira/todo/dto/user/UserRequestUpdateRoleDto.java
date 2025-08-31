package org.nira.todo.dto.user;

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
public class UserRequestUpdateRoleDto {
    @NotEmpty
    private Role role;
}
