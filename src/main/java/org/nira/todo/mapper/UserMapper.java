package org.nira.todo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.nira.todo.dto.user.UserRequestDto;
import org.nira.todo.dto.user.UserResponseDto;
import org.nira.todo.entity.User;

@Mapper
public interface UserMapper {

    UserMapper MAPPER = Mappers.getMapper(UserMapper.class);

    User mapToUser(UserRequestDto userRequestDto);

    UserResponseDto mapToUserResponse(User user);
}
