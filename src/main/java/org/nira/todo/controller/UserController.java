package org.nira.todo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.nira.todo.dto.user.*;
import org.nira.todo.entity.Role;
import org.nira.todo.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "User"
)
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(
            summary = "Create User"
    )
    @ApiResponse(
            responseCode = "201"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto userRequestDto) {
        UserResponseDto createdUser = userService.createUser(userRequestDto);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get User by Id"
    )
    @ApiResponse(
            responseCode = "200"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable("id") Long id) {
        UserResponseDto foundUser = userService.getUserById(id);
        return new ResponseEntity<>(foundUser, HttpStatus.OK);
    }

    @Operation(
            summary = "Get All Users"
    )
    @ApiResponse(
            responseCode = "200"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<Page<UserResponseDto>> getAllUsers(@RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "10") int size) {
        Page<UserResponseDto> users = userService.getAllUsers(page, size);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @Operation(
            summary = "Search Users",
            description = "Search users with flexible criteria including name, username, email, role, and global search"
    )
    @ApiResponse(responseCode = "200")
    @Parameters({
            @Parameter(name = "name", description = "Search by name (partial match)"),
            @Parameter(name = "username", description = "Search by username (partial match)"),
            @Parameter(name = "email", description = "Search by email (partial match)"),
            @Parameter(name = "role", description = "Filter by user role (ADMIN, USER)"),
            @Parameter(name = "search", description = "Global search across name, username, and email"),
            @Parameter(name = "page", description = "Page number (0-based)"),
            @Parameter(name = "size", description = "Page size")
    })
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/search")
    public ResponseEntity<Page<UserResponseDto>> searchUsers(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) Role role,
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        UserSearchCriteria criteria = new UserSearchCriteria(name, username, email, role, search);
        Page<UserResponseDto> users = userService.searchUsers(criteria, page, size);
        return ResponseEntity.ok(users);
    }

    @Operation(
            summary = "Update User"
    )
    @ApiResponse(
            responseCode = "200"
    )
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@RequestBody @Valid UserRequestUpdateDto userRequestUpdateDto,
                                                      @PathVariable("id") Long id) {
        UserResponseDto updatedUser = userService.updateUser(id, userRequestUpdateDto);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @Operation(
            summary = "Delete User"
    )
    @ApiResponse(
            responseCode = "204"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>("Deleted", HttpStatus.NO_CONTENT);
    }

    @Operation(
            summary = "Update User Role"
    )
    @ApiResponse(
            responseCode = "200"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}/role-update")
    public ResponseEntity<UserResponseDto> updateUserRole(@PathVariable Long id,
                                                          @RequestBody @Valid UserRequestUpdateRoleDto userRequestUpdateRoleDto) {
        UserResponseDto updatedUser = userService.roleUpdate(id, userRequestUpdateRoleDto);
        return ResponseEntity.ok(updatedUser);
    }


}
