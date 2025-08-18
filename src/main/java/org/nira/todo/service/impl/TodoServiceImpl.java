package org.nira.todo.service.impl;

import lombok.RequiredArgsConstructor;
import org.nira.todo.dto.TodoRequestDto;
import org.nira.todo.dto.TodoResponseDto;
import org.nira.todo.entity.Todo;
import org.nira.todo.repository.TodoRepo;
import org.nira.todo.service.TodoService;
import org.springframework.stereotype.Service;

import static org.nira.todo.mapper.TodoMapper.MAPPER;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final TodoRepo todoRepo;

    @Override
    public TodoResponseDto addTodo(TodoRequestDto todoRequestDto) {
        Todo todo = MAPPER.mapToTodo(todoRequestDto);
        Todo savedTodo = todoRepo.save(todo);
        TodoResponseDto todoResponseDto = MAPPER.mapToTodoResponseDto(savedTodo);
        return todoResponseDto;
    }
}
