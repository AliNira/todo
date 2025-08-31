package org.nira.todo.service.impl;

import lombok.RequiredArgsConstructor;
import org.nira.todo.dto.user.UserRequestDto;
import org.nira.todo.dto.user.UserRequestUpdateDto;
import org.nira.todo.dto.user.UserRequestUpdateRoleDto;
import org.nira.todo.dto.user.UserResponseDto;
import org.nira.todo.entity.User;
import org.nira.todo.exception.EmailOrUsernameAlreadyExistException;
import org.nira.todo.exception.ResourceNotFoundException;
import org.nira.todo.repository.UserRepo;
import org.nira.todo.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.nira.todo.mapper.UserMapper.MAPPER;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    @Override
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        emailOrUsernameExistenceForUserReqDto(userRequestDto);
        User user = MAPPER.mapToUser(userRequestDto);
        User savedUser = userRepo.save(user);
        return MAPPER.mapToUserResponse(savedUser);
    }

    @Override
    public UserResponseDto getUserById(Long userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return MAPPER.mapToUserResponse(user);
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        List<User> users = userRepo.findAll();
        return users.stream().map(MAPPER::mapToUserResponse).toList();
    }

    @Override
    public UserResponseDto updateUser(Long userId, UserRequestUpdateDto userRequestUpdateDto) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        applyNonNullFields(userRequestUpdateDto, user);
        User savedUser = userRepo.save(user);
        return MAPPER.mapToUserResponse(savedUser);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        userRepo.deleteById(userId);
    }

    @Override
    public UserResponseDto roleUpdate(Long userId, UserRequestUpdateRoleDto userRequestUpdateRoleDto) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        user.setRole(userRequestUpdateRoleDto.getRole());
        User updatedUser = userRepo.save(user);
        return MAPPER.mapToUserResponse(updatedUser);
    }


    private void emailOrUsernameExistenceForUserReqDto(UserRequestDto userRequestDto) {
        if (userRepo.findByEmailOrUsername(userRequestDto.getEmail(), userRequestDto.getUsername()).isPresent()) {
            throw new EmailOrUsernameAlreadyExistException("This Email or Username already exists");
        }
    }

    private void emailOrUsernameExistenceForUserReqUpdateDto(UserRequestUpdateDto userRequestUpdateDto) {
        if (userRepo.findByEmailOrUsername(userRequestUpdateDto.getEmail(), userRequestUpdateDto.getUsername()).isPresent()) {
            throw new EmailOrUsernameAlreadyExistException("This Email or Username already exists");
        }
    }

    private void applyNonNullFields(UserRequestUpdateDto dto, User entity) {
        if (dto.getName() != null) entity.setName(dto.getName());
        if (dto.getEmail() != null) {
            emailOrUsernameExistenceForUserReqUpdateDto(dto);
            entity.setEmail(dto.getEmail());
        }
        if (dto.getUsername() != null) {
            emailOrUsernameExistenceForUserReqUpdateDto(dto);
            entity.setUsername(dto.getUsername());
        }
        if (dto.getPassword() != null) entity.setPassword(dto.getPassword());

    }


}
