package org.nira.todo.service;

import org.nira.todo.dto.TodoRequestDto;
import org.nira.todo.dto.TodoResponseDto;

public interface TodoService {
    TodoResponseDto addTodo(TodoRequestDto todoRequestDto);
}
