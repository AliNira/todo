package org.nira.todo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.nira.todo.dto.user.UserRequestDto;
import org.nira.todo.dto.user.UserRequestUpdateDto;
import org.nira.todo.dto.user.UserRequestUpdateRoleDto;
import org.nira.todo.dto.user.UserResponseDto;
import org.nira.todo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        List<UserResponseDto> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
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
