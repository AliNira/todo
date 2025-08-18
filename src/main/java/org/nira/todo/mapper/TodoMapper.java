package org.nira.todo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.nira.todo.dto.TodoRequestDto;
import org.nira.todo.dto.TodoResponseDto;
import org.nira.todo.entity.Todo;

@Mapper
public interface TodoMapper {
    TodoMapper MAPPER = Mappers.getMapper(TodoMapper.class);

    TodoResponseDto mapToTodoResponseDto(Todo todo);

    Todo mapToTodo(TodoRequestDto todoRequestDto);

}
