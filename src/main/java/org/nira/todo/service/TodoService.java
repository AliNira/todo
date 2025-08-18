package org.nira.todo.service;

import org.nira.todo.dto.TodoRequestDto;
import org.nira.todo.dto.TodoResponseDto;

import java.util.List;

public interface TodoService {

    TodoResponseDto addTodo(TodoRequestDto todoRequestDto);

    TodoResponseDto getTodoById(Long id);

    List<TodoResponseDto> getTodos();
}
