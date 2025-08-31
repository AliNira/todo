package org.nira.todo.service;

import org.nira.todo.dto.user.UserRequestDto;
import org.nira.todo.dto.user.UserRequestUpdateDto;
import org.nira.todo.dto.user.UserRequestUpdateRoleDto;
import org.nira.todo.dto.user.UserResponseDto;

import java.util.List;

public interface UserService {

    UserResponseDto createUser(UserRequestDto userRequestDto);

    UserResponseDto getUserById(Long userId);

    List<UserResponseDto> getAllUsers();

    UserResponseDto updateUser(Long userId, UserRequestUpdateDto userRequestDto);

    void deleteUser(Long userId);

    UserResponseDto roleUpdate(Long userId,  UserRequestUpdateRoleDto userRequestUpdateRoleDto);

}
