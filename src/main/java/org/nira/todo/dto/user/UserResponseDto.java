package org.nira.todo.dto.user;

import lombok.*;
import org.nira.todo.entity.Role;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {

    private Long id;

    private String name;

    private String username;

    private String email;

    private Role role;

}
