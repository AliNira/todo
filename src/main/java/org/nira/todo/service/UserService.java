package org.nira.todo.service;

import org.nira.todo.dto.user.*;
import org.springframework.data.domain.Page;

public interface UserService {

    UserResponseDto createUser(UserRequestDto userRequestDto);

    UserResponseDto getUserById(Long userId);

    Page<UserResponseDto> getAllUsers(int page, int size);

    Page<UserResponseDto> searchUsers(UserSearchCriteria criteria, int page, int size);

    UserResponseDto updateUser(Long userId, UserRequestUpdateDto userRequestDto);

    void deleteUser(Long userId);

    UserResponseDto roleUpdate(Long userId,  UserRequestUpdateRoleDto userRequestUpdateRoleDto);

}
