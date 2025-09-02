package org.nira.todo.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.nira.todo.entity.Role;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserSearchCriteria {
    private String name;
    private String username;
    private String email;
    private Role role;
    private String search; // Global search across name, username, and email
}